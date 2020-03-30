# Hotel Covid - Desktop Environment

The docker platform has a fully functional desktop service based on [Duing](https://github.com/kairops/docker-ubuntu-xrdp-mate-custom/tree/master/duing) project, with an ubuntu 19.04 fully functional and:

- Chromium browser
- Firefox browser
- Intellij IDE

Once the docker dev env is "up and running" use a RDP client to connect to the dockerized desktop.

- For Windows, use build-in "Remote Desktop Environment" application.
- For Mac, use "Microsoft Remote Desktop" application. It can be found in the App Store.
- For Linux, use "Remmina".

Open a connection to:

- Host/port: localhost:3389
- User: ubuntu
- Password: ubuntu


1. Access with RDP to the Ubuntu MATE desktop

    ![Step1 1](img/desktop_step1.png "Step1 1")

    You can open a browser and point it to <http://app;:8080> to view the app running

    ![App running](img/covid_app.png "app running")

2. Open the IntelliJ IDE located on the menu in the upper left corner

    ![Step1 2](img/desktop_step2.png "Step1 2")

3. Accept the Jetbrains Private Policy license

    ![Step1 3](img/desktop_step3.png "Step1 3")

4. Wait for the IDE to start

    ![Step1 4](img/desktop_step4.png "Step1 4")

5. Click on "Import" project

    ![Step1 5](img/desktop_step5.png "Step1 5")

6. Select "/workspace" directory of the system

    ![Step1 6](img/desktop_step6.png "Step1 6")

    Note that this directory of the container is connected to your workstation file system using a docker volume, so you can find the source code here

7. Import the project iusing a Maven model

    ![Step1 7](img/desktop_step7.png "Step1 7")

8. Wait for the IDE for the initial warmup

    ![Step1 8](img/desktop_step8.png "Step1 8")

9. Add a new remote debugging configuration

    ![Step1 9](img/desktop_step9.png "Step1 9")

10. Add a new application configuration (it's not yet verified)

    ![Step1 10](img/desktop_step10.png "Step1 10")

11. Click on the debug icon on the upper right corner of the IDE

    ![Step1 11](img/desktop_step11.png "Step1 11")

12. Check that you are connected to the remote app

    ![Step1 12](img/desktop_step12.png "Step1 12")
