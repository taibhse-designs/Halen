@echo off

echo #####################################################################
echo #                                                                   # 
echo #          ^=^=^=           +++        ^`  ___  ^'        _/7            #    
echo #         (o o)         (o o)      -  (O o)  -      (o o)           #     
echo #     ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo       #
echo #                                                                   #
echo #####################################################################
echo.  
echo This script takes in revision details and applies them to the jar 
echo file inside the dist folder and then proceeds to upload the dist 
echo folder to github and deploy the files onto the taibhse server.
echo.
echo A series of questions will be asked prior to deployment.
echo Please answer all questions correctly or this script may fail 
echo to deploy the project correctly.
echo.     
echo #####################################################################
pause
echo.
for /R "%~dp0\dist\" %%F IN (*.jar) DO Echo %%F
echo.
echo Find project jar above (first line), ensure new deployed version 
echo number is higher. numbering format is x1.x2.x3 where
echo x1 signifies new features added, this resets x2 and x3 to 0
echo x2 signifies fixed major bugs that would break the program
echo x3 signifies fixes to minor issues,
echo.
echo #####################################################################
set /p version="Enter build version number (keep format x1.x2.x3): "
echo.
echo %version%
ren "%~dp0\dist\*.jar" "Halen %version%.jar"
echo.
set /p changes="List changes/updates made from previous version: "
echo.
REM ROBOCOPY "%~dp0\dist\" "C:\Users\TAIBHSE\OneDrive\Halen Releases" *.* /PURGE /S /NP /ZB /R:5 /TS /FP
Del "%~dp0\dist\README.TXT"

git add %~dp0\dist\*
git commit -m "%changes%"
git push origin master

echo #####################################################################
echo DEPLOYING UPDATED FILES TO SERVER.....
echo #####################################################################
REM rename current active jar on server to backup
ren "\\Friday\Friday-Files\portables\MyPrograms\Halen\*.jar" "*.jar.backup"

REM copy everything in the dist folder to the server
copy "%~dp0\dist\*" "\\Friday\Friday-Files\portables\MyPrograms\Halen"
REM copy library files over
copy "%~dp0\dist\lib\*" "\\Friday\Friday-Files\portables\MyPrograms\Halen\lib\"

echo #####################################################################
echo DEPLOYMENT COMPLETE - pushed to github and deployed on server
echo #####################################################################

pause