#  Docker Network Driver Plugin for veth

## Debugging

Run io.pne.veth.server.ServerApplication
 
Create `/etc/docker/plugins/veth.spec` with the content shows below:

```
tcp://localhost:9090
```

Create Docker Network `dns-1-network`:

```
docker network create \
    --driver  veth \
    --subnet  192.168.4.0/24 \
    --gateway 192.168.4.1 \
    -o ip.pne.veth.interface.suffix=dns-1 \
    dns-1-network
```

Run container `dns-1`:

```
docker run -it --rm \
    --name dns-1  \
    --ip 192.168.3.2 \
    --mac-address 62:7b:0b:7d:54:8d \
    --network dns-1 \
    alpine /bin/sh
```


How the Plugin creates the network

```
ip link add name dns-1.h type veth peer name dns-1.c
ip link set dev dns-1.c address 62:7b:0b:7d:54:8d
ip addr add dev dns-1.h 192.168.3.1/24
ip link set dev dns-1.h up
```

## Remote API Overview


### Create Network

![Create Network](https://github.com/evsinev/docker-network-veth/raw/master/doc/remote-api-create-network.svg)


### Create Endpoint

![Create Endpoint](https://github.com/evsinev/docker-network-veth/raw/master/doc/remote-api-create-endpoint.svg)
