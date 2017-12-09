/**
 * Copyright (C) 2017 Javier Tarazaga Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.circlecoin.internal.di.modules

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import io.circlecoin.AndroidApplication
import io.circlecoin.UIThread
import io.circlecoin.data.executor.JobExecutor
import io.circlecoin.data.prefs.StringPreference
import io.circlecoin.domain.executor.PostExecutionThread
import io.circlecoin.domain.executor.ThreadExecutor
import javax.inject.Singleton

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
class ApplicationModule(private val application: AndroidApplication) {

  @Provides
  @Singleton internal fun provideApplicationContext(): Context {
    return this.application
  }

  @Provides
  @Singleton internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
    return jobExecutor
  }

  @Provides
  @Singleton internal fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
    return uiThread
  }

  @Provides
  @Singleton internal fun provideSharedPreferences(): SharedPreferences {
    return this.application.getSharedPreferences("Instasearch", MODE_PRIVATE)
  }

  @Provides
  @Singleton internal fun providesAuthToken(preferences: SharedPreferences): StringPreference {
    return StringPreference(preferences, "auth_token")
  }
}
