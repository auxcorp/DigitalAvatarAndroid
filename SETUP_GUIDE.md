# 🎬 Digital Avatar Android App - Complete Setup Guide

## Overview

This project implements a complete multi-agent digital avatar system for Android with integration to Duix-Avatar backend services. It allows you to create personalized AI avatars, synthesize speech, and generate videos.

## 🚀 Quick Start (5 Minutes)

### Prerequisites
- Android Studio Hedgehog or newer
- - Android SDK 34
  - - Kotlin 1.9+
    - - Duix-Avatar backend running (see Backend Setup below)
     
      - ### Step 1: Clone & Setup
     
      - ```bash
        # Clone the repository
        git clone https://github.com/auxcorp/DigitalAvatarAndroid.git
        cd DigitalAvatarAndroid

        # Open in Android Studio
        open -a "Android Studio" .
        ```

        ### Step 2: Wait for Gradle Sync

        Android Studio will automatically sync dependencies. Wait for it to complete.

        ### Step 3: Configure Backend Connection

        Edit `app/build.gradle.kts` - Configure your backend IP:

        ```gradle
        buildConfigField("String", "API_BASE_URL", "\"http://YOUR_BACKEND_IP:8383/\"")
        buildConfigField("String", "TTS_API_URL", "\"http://YOUR_BACKEND_IP:18180/\"")
        ```

        For emulator: Use `http://10.0.2.2:PORT/` (emulator default gateway)
        For device: Use your machine's actual IP (e.g., `http://192.168.1.100:PORT/`)

        ### Step 4: Build & Run

        ```bash
        # Build the APK
        ./gradlew build

        # Run on emulator/device
        ./gradlew installDebug
        ```

        ---

        ## 💻 Backend Setup

        ### Prerequisites
        - Docker Desktop (Windows/Mac) or Docker Engine (Linux)
        - - NVIDIA GPU with drivers installed
          - - 130GB disk space
            - - 32GB RAM recommended
             
              - ###  Installation
             
              - ####  1. Clone Duix-Avatar
             
              - ```bash
                git clone https://github.com/duixcom/Duix-Avatar.git
                cd Duix-Avatar/deploy
                ```

                ####  2. Deploy Services

                **Windows/Mac with Docker Desktop:**

                ```bash
                # Update WSL (Windows only)
                wsl --update

                # Start services
                docker-compose up -d

                # Verify services
                docker ps  # Should show 3 services running
                ```

                **Ubuntu 22.04:**

                ```bash
                # Install NVIDIA Container Toolkit
                distribution=$(. /etc/os-release;echo $ID$VERSION_ID) && \
                curl -s -L https://nvidia.github.io/libnvidia-container/gpgkey | sudo apt-key add - && \
                curl -s -L https://nvidia.github.io/libnvidia-container/$distribution/libnvidia-container.list | \
                  sudo tee /etc/apt/sources.list.d/nvidia-container-toolkit.list

                sudo apt-get update
                sudo apt-get install -y nvidia-container-toolkit
                sudo nvidia-ctk runtime configure --runtime=docker
                sudo systemctl restart docker

                # Deploy
                cd deploy
                docker-compose -f docker-compose-linux.yml up -d
                ```

                ####  3. Verify Backend

                Check service logs:

                ```bash
                # Check if services are healthy
                curl http://127.0.0.1:18180/v1/models
                # Should return model info

                curl http://127.0.0.1:8383/health
                # Should return OK
                ```

                ---

                ## 📁 Project Structure

                ```
                DigitalAvatarAndroid/
                ├── app/
                │   ├── build.gradle.kts                    # Build configuration
                │   ├── src/
                │   │   ├── main/
                │   │   │   ├── AndroidManifest.xml        # App manifest
                │   │   │   ├── java/com/example/digitalavatar/
                │   │   │   │   ├── agents/                # Multi-agent system
                │   │   │   │   │   ├── AvatarAgent.kt
                │   │   │   │   │   ├── SpeechAgent.kt
                │   │   │   │   │   ├── VideoSynthesisAgent.kt
                │   │   │   │   │   └── DigitalAvatarOrchestrator.kt
                │   │   │   │   ├── api/                   # API client & models
                │   │   │   │   │   ├── DuixAvatarApiService.kt
                │   │   │   │   │   ├── ApiModels.kt
                │   │   │   │   │   └── ApiClient.kt
                │   │   │   │   ├── data/                  # Data layer
                │   │   │   │   │   ├── local/            # Room database
                │   │   │   │   │   ├── models/           # Data models
                │   │   │   │   │   └── repository/       # Data repository
                │   │   │   │   ├── ui/                    # UI layer (Compose)
                │   │   │   │   │   ├── screens/          # UI screens
                │   │   │   │   │   ├── viewmodels/       # ViewModels
                │   │   │   │   │   └── components/       # Reusable components
                │   │   │   │   ├── utils/                 # Utilities
                │   │   │   │   ├── di/                    # Dependency injection
                │   │   │   │   └── MainActivity.kt
                │   │   │   └── res/                       # Android resources
                │   │   └── test/                          # Unit tests
                │   └── AndroidManifest.xml
                ├── build.gradle.kts                       # Root build configuration
                ├── SETUP_GUIDE.md                         # This file
                ├── README.md                              # Project README
                └── LICENSE

                ```

                ---

                ## 📝 Implementation Checklist

                - [ ] **Phase 1: Backend**
                - [ ]   - [ ] Install Docker
                - [ ]     - [ ] Clone Duix-Avatar
                - [ ]   - [ ] Run docker-compose up -d
                - [ ]     - [ ] Verify services running
               
                - [ ] - [ ] **Phase 2: Android Setup**
                - [ ]   - [ ] Install Android Studio
                - [ ]     - [ ] Clone this repository
                - [ ]   - [ ] Sync Gradle
                - [ ]     - [ ] Configure backend IP in build.gradle.kts
               
                - [ ] - [ ] **Phase 3: Core Features**
                - [ ]   - [ ] Implement API Client (Retrofit)
                - [ ]     - [ ] Implement Data Layer (Room DB)
                - [ ]   - [ ] Implement Agents (Avatar, Speech, Video)
                - [ ]     - [ ] Implement Orchestrator
               
                - [ ] - [ ] **Phase 4: UI**
                - [ ]   - [ ] Avatar Creation Screen
                - [ ]     - [ ] Video Generation Screen
                - [ ]   - [ ] Avatar List Screen
                - [ ]     - [ ] Video Playback Screen
                - [ ]   - [ ] Progress/Loading dialogs
               
                - [ ]   - [ ] **Phase 5: Testing & Deployment**
                - [ ]     - [ ] Unit tests
                - [ ]   - [ ] Integration tests
                - [ ]     - [ ] Generate APK
                - [ ]   - [ ] Test on device
               
                - [ ]   ---
               
                - [ ]   ## 🔧 Configuration
               
                - [ ]   ### API Endpoints
               
                - [ ]   The app communicates with these Duix-Avatar services:
               
                - [ ]   | Service | Port | Endpoint | Purpose |
                - [ ]   |---------|------|----------|---------|
                - [ ]   | ASR | 18180 | `v1/invoke` | Audio to text, model training |
                - [ ]   | TTS | 18180 | `v1/invoke` | Text to speech synthesis |
                - [ ]   | Video | 8383 | `easy/submit` | Video synthesis |
                - [ ]   | Video | 8383 | `easy/query` | Check synthesis progress |
               
                - [ ]   ###  IP Configuration
               
                - [ ]   **For Android Emulator:**
                - [ ]   ```gradle
                - [ ]   buildConfigField("String", "API_BASE_URL", "\"http://10.0.2.2:8383/\"")
                - [ ]   ```
               
                - [ ]   **For Physical Device (same WiFi):**
                - [ ]   ```gradle
                - [ ]   buildConfigField("String", "API_BASE_URL", "\"http://192.168.1.100:8383/\"")
                - [ ]   # Replace 192.168.1.100 with your machine's local IP
                - [ ]   ```
               
                - [ ]   **To find your IP:**
                - [ ]   ```bash
                - [ ]   # Mac/Linux
                - [ ]   ifconfig | grep "inet " | grep -v 127.0.0.1
               
                - [ ]   # Windows
                - [ ]   ipconfig | findstr IPv4
                - [ ]   ```
               
                - [ ]   ---
               
                - [ ]   ## 🛠️ Development Workflow
               
                - [ ]   ### Building
               
                - [ ]   ```bash
                - [ ]   # Debug build (for development)
                - [ ]   ./gradlew build
               
                - [ ]   # Release build (for production)
                - [ ]   ./gradlew buildRelease
               
                - [ ]   # Install on device
                - [ ]   ./gradlew installDebug
                - [ ]   ```
               
                - [ ]   ### Testing
               
                - [ ]   ```bash
                - [ ]   # Run unit tests
                - [ ]   ./gradlew test
               
                - [ ]   # Run instrumented tests
                - [ ]   ./gradlew connectedAndroidTest
               
                - [ ]   # Run specific test
                - [ ]   ./gradlew test -k AvatarAgentTest
                - [ ]   ```
               
                - [ ]   ### Debugging
               
                - [ ]   ```bash
                - [ ]   # View logs
                - [ ]   adb logcat -s DigitalAvatar
               
                - [ ]   # Clear data
                - [ ]   adb shell pm clear com.example.digitalavatar
               
                - [ ]   # View database
                - [ ]   adb shell run-as com.example.digitalavatar sqlite3 databases/avatar.db
                - [ ]   ```
               
                - [ ]   ---
               
                - [ ]   ## 📱 App Features
               
                - [ ]   ### 1. Avatar Creation
                - [ ]   - Select personal video
                - [ ]   - Extract features from video
                - [ ]   - Train avatar model
                - Store locally with thumbnail
               
                - ### 2. Speech Synthesis
                - - Enter custom script
                  - - Synthesize audio with avatar voice
                    - - Adjust speech parameters (speed, pitch)
                      - - Support for multiple languages
                       
                        - ### 3. Video Generation
                        - - Select avatar & audio
                          - - Synthesize video with lip-sync
                            - - Monitor generation progress
                              - - Download & play videos
                               
                                - ### 4. Video Management
                                - - View all generated videos
                                  - - Play videos in-app
                                    - - Delete videos
                                    - Export for sharing
                                   
                                    - ---

                                    ## 🎯 API Usage Examples

                                    ### Create Avatar

                                    ```kotlin
                                    val avatarAgent = AvatarAgent(context, apiService, avatarDao, fileManager)

                                    val result = avatarAgent.createAvatar(
                                        videoPath = "/sdcard/myavatar.mp4",
                                        name = "My Avatar",
                                        description = "Personal AI avatar"
                                    )

                                    result.onSuccess { avatar ->
                                        Log.d("Avatar", "Created: ${avatar.uuid}")
                                    }.onFailure { error ->
                                        Log.e("Avatar", "Error: ${error.message}")
                                    }
                                    ```

                                    ### Generate Speech

                                    ```kotlin
                                    val speechAgent = SpeechAgent(apiService, audioProcessor, fileManager)

                                    val audioResult = speechAgent.generateSpeech(
                                        text = "Hello, I am your personal AI avatar!",
                                        speakerId = avatar.speakerId,
                                        referenceAudio = avatar.asrFormatAudioUrl,
                                        referenceText = avatar.referenceText
                                    )

                                    audioResult.onSuccess { audioPath ->
                                        Log.d("Speech", "Audio saved: $audioPath")
                                    }
                                    ```

                                    ### Synthesize Video

                                    ```kotlin
                                    val videoAgent = VideoSynthesisAgent(...)

                                    val videoResult = videoAgent.synthesizeVideo(
                                        audioPath = audioPath,
                                        avatarVideoPath = avatar.videoPath,
                                        avatarUuid = avatar.uuid,
                                        script = "Hello, I am your personal AI avatar!"
                                    )

                                    videoResult.onSuccess { videoUrl ->
                                        playVideo(videoUrl)
                                    }
                                    ```

                                    ---

                                    ## 🐛 Troubleshooting

                                    ### Issue: "Cannot connect to backend"

                                    **Solution:**
                                    1. Verify backend is running: `docker ps`
                                    2. 2. Check IP configuration in build.gradle.kts
                                       3. 3. Verify firewall allows connections
                                          4. 4. Test connectivity: `curl http://YOUR_IP:8383/health`
                                            
                                             5. ### Issue: "Model training fails"
                                            
                                             6. **Solution:**
                                             7. 1. Check backend logs: `docker logs duix-avatar-gen-video`
                                                2. 2. Verify video format (MP4, H.264)
                                                   3. 3. Ensure video is at least 5 seconds
                                                      4. 4. Check disk space: `df -h`
                                                        
                                                         5. ### Issue: "Audio synthesis timeout"
                                                        
                                                         6. **Solution:**
                                                         7. 1. Check network latency
                                                            2. 2. Reduce text length
                                                               3. 3. Increase timeout in ApiClient.kt (connectTimeout, readTimeout)
                                                                  4. 4. Check backend memory: `docker stats`
                                                                    
                                                                     5. ### Issue: "App crashes on video playback"
                                                                    
                                                                     6. **Solution:**
                                                                     7. 1. Check video file exists
                                                                        2. 2. Verify video codec compatibility
                                                                           3. 3. Update Media3 library
                                                                           4. Clear app cache: `adb shell pm clear com.example.digitalavatar`
                                                                          
                                                                           5. ---
                                                                          
                                                                           6. ## 📚 Resources
                                                                          
                                                                           7. - **Duix-Avatar GitHub**: https://github.com/duixcom/Duix-Avatar
                                                                              - - **Android Docs**: https://developer.android.com
                                                                              - **Kotlin Coroutines**: https://kotlinlang.org/docs/coroutines-overview.html
                                                                              - **Room Database**: https://developer.android.com/training/data-storage/room
                                                                              - - **Jetpack Compose**: https://developer.android.com/jetpack/compose
                                                                               
                                                                                - ---

                                                                                ## 📞 Support

                                                                                For issues:
                                                                                1. Check troubleshooting section
                                                                                2. 2. Review GitHub Issues
                                                                                   3. 3. Contact: james@duix.com (Duix-Avatar support)
                                                                                     
                                                                                      4. ---
                                                                                     
                                                                                      5. ## 📄 License
                                                                                     
                                                                                      6. MIT License - See LICENSE file for details
                                                                                     
                                                                                      7. ---
                                                                                     
                                                                                      8. **Last Updated**: December 2025
                                                                                      9. **Version**: 1.0.0
