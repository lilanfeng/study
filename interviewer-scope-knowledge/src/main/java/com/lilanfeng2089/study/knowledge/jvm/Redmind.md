

社群真题1：元空间会产生内存溢出么？在什么情况下会产生内存溢出？
    java8  及以后的版本使用Metaspace来代替永久代，Metaspace是方法区在HotSpot中的实现，它与永久代最大区别在于，Metaspace并不在虚拟机内存中而是使用本地内存也就是在JDK8中,classe
metadata(the virtual machines internal presentation of Java  class),被存储在叫做Metaspace的
native memory.
永久代（java 8 后被元空间Metaspace取代了）存放了以下信息：