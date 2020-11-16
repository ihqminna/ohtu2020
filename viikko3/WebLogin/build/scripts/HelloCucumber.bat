@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  HelloCucumber startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and HELLO_CUCUMBER_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\HelloCucumber.jar;%APP_HOME%\lib\spark-template-velocity-2.7.1.jar;%APP_HOME%\lib\spark-core-2.7.1.jar;%APP_HOME%\lib\selenium-java-3.14.0.jar;%APP_HOME%\lib\selenium-firefox-driver-3.14.0.jar;%APP_HOME%\lib\selenium-chrome-driver-3.14.0.jar;%APP_HOME%\lib\htmlunit-driver-2.33.0.jar;%APP_HOME%\lib\selenium-edge-driver-3.14.0.jar;%APP_HOME%\lib\selenium-ie-driver-3.14.0.jar;%APP_HOME%\lib\selenium-opera-driver-3.14.0.jar;%APP_HOME%\lib\selenium-safari-driver-3.14.0.jar;%APP_HOME%\lib\selenium-support-3.14.0.jar;%APP_HOME%\lib\selenium-remote-driver-3.14.0.jar;%APP_HOME%\lib\selenium-api-3.14.0.jar;%APP_HOME%\lib\slf4j-api-1.7.13.jar;%APP_HOME%\lib\jetty-webapp-9.4.6.v20170531.jar;%APP_HOME%\lib\websocket-server-9.4.6.v20170531.jar;%APP_HOME%\lib\jetty-servlet-9.4.6.v20170531.jar;%APP_HOME%\lib\jetty-security-9.4.6.v20170531.jar;%APP_HOME%\lib\jetty-server-9.4.6.v20170531.jar;%APP_HOME%\lib\websocket-servlet-9.4.6.v20170531.jar;%APP_HOME%\lib\velocity-1.7.jar;%APP_HOME%\lib\byte-buddy-1.8.15.jar;%APP_HOME%\lib\commons-exec-1.3.jar;%APP_HOME%\lib\htmlunit-2.33.jar;%APP_HOME%\lib\httpmime-4.5.6.jar;%APP_HOME%\lib\httpclient-4.5.6.jar;%APP_HOME%\lib\commons-codec-1.10.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\guava-25.0-jre.jar;%APP_HOME%\lib\httpcore-4.4.10.jar;%APP_HOME%\lib\okhttp-3.10.0.jar;%APP_HOME%\lib\okio-1.14.1.jar;%APP_HOME%\lib\javax.servlet-api-3.1.0.jar;%APP_HOME%\lib\websocket-client-9.4.12.v20180830.jar;%APP_HOME%\lib\jetty-client-9.4.12.v20180830.jar;%APP_HOME%\lib\jetty-http-9.4.12.v20180830.jar;%APP_HOME%\lib\websocket-common-9.4.12.v20180830.jar;%APP_HOME%\lib\jetty-io-9.4.12.v20180830.jar;%APP_HOME%\lib\jetty-xml-9.4.12.v20180830.jar;%APP_HOME%\lib\websocket-api-9.4.12.v20180830.jar;%APP_HOME%\lib\commons-collections-3.2.1.jar;%APP_HOME%\lib\commons-lang-2.4.jar;%APP_HOME%\lib\jsr305-1.3.9.jar;%APP_HOME%\lib\checker-compat-qual-2.0.0.jar;%APP_HOME%\lib\error_prone_annotations-2.1.3.jar;%APP_HOME%\lib\j2objc-annotations-1.1.jar;%APP_HOME%\lib\animal-sniffer-annotations-1.14.jar;%APP_HOME%\lib\xalan-2.7.2.jar;%APP_HOME%\lib\commons-lang3-3.8.jar;%APP_HOME%\lib\commons-text-1.4.jar;%APP_HOME%\lib\htmlunit-core-js-2.33.jar;%APP_HOME%\lib\neko-htmlunit-2.33.jar;%APP_HOME%\lib\htmlunit-cssparser-1.2.0.jar;%APP_HOME%\lib\commons-io-2.6.jar;%APP_HOME%\lib\commons-net-3.6.jar;%APP_HOME%\lib\jetty-util-9.4.12.v20180830.jar;%APP_HOME%\lib\serializer-2.7.2.jar;%APP_HOME%\lib\xercesImpl-2.12.0.jar;%APP_HOME%\lib\xml-apis-1.4.01.jar


@rem Execute HelloCucumber
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %HELLO_CUCUMBER_OPTS%  -classpath "%CLASSPATH%" ohtu.Main %*

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable HELLO_CUCUMBER_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%HELLO_CUCUMBER_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
