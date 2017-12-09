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
package io.circlecoin

import android.app.Application

import com.crashlytics.android.Crashlytics

import io.circlecoin.internal.di.components.ApplicationComponent
import io.circlecoin.internal.di.components.DaggerApplicationComponent
import io.circlecoin.internal.di.modules.ApplicationModule
import io.fabric.sdk.android.Fabric


/**
 * Android Main Application
 */
class AndroidApplication : Application() {

    var applicationComponent: ApplicationComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()

        //    if (LeakCanary.isInAnalyzerProcess(this)) {
        //      // This process is dedicated to LeakCanary for heap analysis.
        //      // You should not init your app in this process.
        //      return;
        //    }

        this.initializeCrashReporting()
        this.initializeInjector()
        // this.initializeLeakDetection();
    }

    private fun initializeCrashReporting() {
        Fabric.with(this, Crashlytics())
    }

    private fun initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    //  private void initializeLeakDetection() {
    //    LeakCanary.install(this);
    //  }
}
