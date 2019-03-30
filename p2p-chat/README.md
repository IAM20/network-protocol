## P2P chatting application
--------

<p align=center>
<img src="pic/example.gif"/>
</p>

### How to build?
> If you have maven
```shell
$ mvn clean compile assembly:single
```
> Else
```shell
$ ./mvnw clean compile assembly:single
```

### Usage
```
h | help                         : manuals
r | reply                        : reply to the most recent sender
(c | connect) ip-address(?:port) : connect to ip-address, port default port is 18080
q | quit                         : close the connection
Q | QUIT                         : close the application
```

