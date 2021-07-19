application.properties和application.yml文件能放在以下四个位置。
springboot配置文件优先级：
(1) 外置，在相对于应用程序运行目录的/config子目录里。
(2) 外置，在应用程序运行的目录里。
(3) 内置，在config包内。
(4) 内置，在Classpath根目录。

1、项目启动默认加载classpath目录下default_config.properties文件
2、数据库默认h2，若未配置数据文件地址则默认用户目录下/mutool/data.db文件
3、h2数据库默认用户密码为root/root
4、项目可通过设置application配置文件覆盖属性
