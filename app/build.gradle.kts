plugins {
      id("com.android.application")
          id("org.jetbrains.kotlin.android")
              id("com.google.devtools.ksp")
                  id("kotlin-kapt")
}

android {
      namespace = "com.example.digitalavatar"
      compileSdk = 34

      defaultConfig {
                applicationId = "com.example.digitalavatar"
                minSdk = 26
                targetSdk = 34
                versionCode = 1
                versionName = "1.0.0"

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

                buildConfigField("String", "API_BASE_URL", "\"http://10.0.2.2:8383/\"")
                        buildConfigField("String", "TTS_API_URL", "\"http://10.0.2.2:18180/\"")
      }

          buildTypes {
                    release {
                                  isMinifyEnabled = true
                                  proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                    }
          }

              compileOptions {
                        sourceCompatibility = JavaVersion.VERSION_11
                        targetCompatibility = JavaVersion.VERSION_11
              }

                  kotlinOptions {
                            jvmTarget = "11"
                  }

                      buildFeatures {
                                compose = true
                                viewBinding = true
                      }

                          composeOptions {
                                    kotlinCompilerExtensionVersion = "1.5.1"
                          }
}

dependencies {
      implementation("androidx.core:core-ktx:1.12.0")
          implementation("androidx.appcompat:appcompat:1.6.1")
              implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
                  implementation("androidx.compose.ui:ui:1.5.4")
                      implementation("androidx.compose.material3:material3:1.1.2")
                          implementation("androidx.activity:activity-compose:1.8.0")
                              implementation("androidx.navigation:navigation-compose:2.7.5")
                                  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

                                          implementation("com.squareup.retrofit2:retrofit:2.10.0")
                                              implementation("com.squareup.retrofit2:converter-gson:2.10.0")
                                                  implementation("com.squareup.okhttp3:okhttp:4.11.0")
                                                      implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

                                                              implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

                                                                      implementation("androidx.room:room-runtime:2.6.1")
                                                                          implementation("androidx.room:room-ktx:2.6.1")
                                                                              ksp("androidx.room:room-compiler:2.6.1")

                                                                                      implementation("androidx.media3:media3-exoplayer:1.1.1")
                                                                                          implementation("androidx.media3:media3-ui:1.1.1")

                                                                                                  implementation("androidx.work:work-runtime-ktx:2.8.1")
                                                                                                      
                                                                                                          implementation("com.google.dagger:hilt-android:2.48")
                                                                                                              kapt("com.google.dagger:hilt-compiler:2.48")
                                                                                                                  
                                                                                                                      implementation("io.coil-kt:coil-compose:2.5.0")
                                                                                                                          
                                                                                                                              testImplementation("junit:junit:4.13.2")
                                                                                                                                  androidTestImplementation("androidx.test.ext:junit:1.1.5")
                                                                                                                                      androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.4")
}
