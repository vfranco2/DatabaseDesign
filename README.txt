This is Noods, a ramen noodle recipe database application created by Vlad Franco as part of my database class project.

USAGE INSTRUCTIONS
1) Install XAMPP, only Apache and MySQL need to be selected during install https://www.apachefriends.org/download.html
2) Install Android Studio
3) Download the project from our Github (https://github.com/vfranco2/Noods)
4) Add files from PHP folder to C:\xampp\htdocs (if on Windows)
5) In SQL folder, run gentables.sql in PHPMyAdmin to build the database with default values
6) Load project in Android Studio, use the navigation bar at the left to navigate to app\java\com.example.noods\MainActivity
7) Change the value of String ip on line 40 to the current IPv4 address of the device running XAMPP (unfortunately, we cannot retrieve the IP address of the device for PHP scripts automatically when using localhost. A domain would allow this step to be skipped, but we do not have the funds.)
8) Connect your android phone (or use the built-in emulator), and click the green "Run" arrow found in the toolbar near the top
