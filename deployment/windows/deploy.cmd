@ECHO ON

IF EXIST "C:\TheHumans" (
RMDIR /s /q "C:\TheHumans"
)

MKDIR "C:\TheHumans\bin"
MKDIR "C:\TheHumans\lib"
MKDIR "C:\TheHumans\assets"

XCOPY "E:\Projects\TheHumans\assets" "C:\TheHumans\assets" /s/h/e/k/f/c
XCOPY "E:\Projects\TheHumans\out\artifacts\TheHumansJar\TheHumansJar.jar" "C:\TheHumans\bin\" /s/h/e/k/f/c
XCOPY "E:\Projects\TheHumans\lib\native-window" "C:\TheHumans\lib" /s/h/e/k/f/c

java -jar E:\Projects\TheHumans\deployment\jarsplice-0.40.jar