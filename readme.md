#  Docker Network Driver Plugin for veth

## Debugging

Run io.pne.veth.server.ServerApplication
 
Create `/etc/docker/plugins/veth.spec` with the content shows below:

```
tcp://localhost:9090
```

Create  `veth` network:

```
docker network create --driver veth veth0
```