Hayabusa
========

We all love saving time. Hayabusa B2 is a productivity tool that gives you a new way to do what you want to do in Blackboard Learn, QUICKLY!. Hayabusa features:
* Quickly search and execute the following:
  * Sys admin pages (admin only)
  * All courses (admin only)
  * My courses
  * Themes (admin only) - coming soon
  * Personal language packs
  * Email
  * Password, personal information
* Voice recognition for Chrom browsers version 11 and above
* Keyboard shortcuts
* Extensibility to make Hayabusa smarter and do more

## Keyboard Shortcuts
* 'shift + up' - Open command bar
* 'shirt + down', 'esc' - Close commmand bar
* 'enter' - Execute command

## Requirements
* Blackboard Learn version: 9.1 SP10
* Chrome version 25 (for speech recognition)

## Gradle Tasks
Now that your development environment is set up, building, testing, and deploying using the Gradle start up script, gradlew/gradlew.bat is easy. Here are a list of general tasks that you might use frequently.

* eclipse - Generates all Eclipse files.
* classes - Just builds the project.
* test - Runs the unit tests.
* build - Assembles in build/libs and tests this project.
* clean - Deletes the build directory.
* deployB2 - Deploy the Building Block into your Learn instance. (e.g. ./gradlew -Dserver=localhost deployB2)

Note: deployB2 requires 'Starting Block' B2 to be installed on your development server.

## Extended B2 Exention Points
* blackboard.platform.renderingHook
