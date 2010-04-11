#summary Examples build and deploy instructions
=In order to build and deploy Java EE examples you have to=

==Download [https://glassfish.dev.java.net/ GlassFish] Enterprise Server v3==

    http://www.sun.com/software/products/appsrvr/get_it.jsp

    Choose "installer" version over "zip" one unless you know what you are doing.

==Install [https://glassfish.dev.java.net/ GlassFish] Enterprise Server v3==
===Mac Os X users===
        *  Open terminal
          *  launch Spotlight
          *  type 'Terminal'
          *  click Enter
        *  Change current directory to the one where [https://glassfish.dev.java.net/ GlassFish] was downloaded
            `cd ~/Downloads`
        *  Make install script executable
            `chmod +x sges-v3-unix.sh`
        *  Run installation process
            `./sges-v3-unix.sh`
        *  Go to the newly opened Install Wizard window
          *  accept agreement
          *  click next until the process end (use defaults)
          *  wait for a 3 to 10 minutes
          *  you can skip registration

==Install Maven==
===Mac Os X users===
You probably already have maven in your environment

To check this open terminal and type

`mvn -v`

You should see something like

`Apache Maven 2.2.0`

`Java version ...`

In case you don't see this

    *  download zip version from http://maven.apache.org/download.html
    *  unpack it (by just opening it) and copy the content to, let say, ~/development directory
    *  extend your PATH environment variable to point to newly downloaded maven directory. In your terminal
        `export PATH=~/development/apache-maven-2.2.1/bin/:$PATH`

==Install Mercurial (distributed version control system)==
===Mac Os X users===

You are probably already have mercurial in your environment. To check this open terminal and type

`hg --version`

You should see something like

`Mercurial Distributed SCM (version 1.5+20100307)`

In case you don't see this, download mercurial from

http://mercurial.selenic.com/

==Get source code==

In your terminal, go to the your projects directory, let say

`cd ~/development/projects`

Get a local copy of the practicaljava

`hg clone https://practicaljava.googlecode.com/hg/ practicaljava`



=To build your source code and deploy result on [https://glassfish.dev.java.net/ GlassFish]=

    *  Open terminal
    *  Check that all necessary environment variables are set
    ====Mac Os X:====
      *  Maven binary in your PATH
        `export PATH=~/development/apache-maven-2.2.1/bin/:$PATH`
      *  Java home is set to Java 6
        `export JAVA_HOME="/System/Library/Frameworks/JavaVM.framework/Versions/1.6.0/Home"`
      *  If you installed [https://glassfish.dev.java.net/ GlassFish] to specific directory and not the default one
        `export GLASSFISH_HOME=/path/to/glassfish`
         You can put these lines in ~/.profile file for future terminal sessions

      *  Change current directory to the specific lesson directory

        `cd ~/development/projects/practicaljava/lesson26`

    *  Build artifact and deploy it to the [https://glassfish.dev.java.net/ GlassFish] server

        `mvn install -Dserver=glassfish`


You can ommit `-Dserver=glassfish` part and that will disable auto deployment
    `mvn install`

You can build artifact, deploy and run integration tests
    `mvn install -Dserver=glassfish -P IT`

Or just run tests
    `mvn verify -P IT`

If you change your source code, add and/or remove extra classes it is better to clean previous builds result and use

    `mvn clean install`

After your first deploy you can open [https://glassfish.dev.java.net/ GlassFish] admin console

To do this, type the following in your internet browser address line

http://localhost:5000/

You will be prompted to register the server, you can skip this step

On the left pane there is "Applications" folder icon, click it

On the main window you will see table with deployed modules/applications

|| Name         ||  Enabled  ||  Engines || Action                 ||
|| lesson26-1.0 ||    yes    ||  ejb     || Redeploy Restart       ||

During your practice more modules/applications will be appeared on that screen