package com.cust.demo;

/**
 * java volatile 关键字------转录自博客 http://www.cnblogs.com/dolphin0520/p/3920373.html
 *
 * 一、内存模型相关观念
 *          计算机在执行程序时，每条指令都是在CPU中执行的，而执行指令的过程涉及数据的读取和写入。
 *      由于程序运行过程中的临时数据时存放在主存（物理内存）当中，这时候就存在一个问题：由于CPU的
 *      执行速度很快，而从内存读取数据和向内存写入数据的过程跟CPU执行指令的速度比起来要慢的多，
 *          因此在CPU里就有了高速缓存，也就是当程序运行过程中，会将运算需要的数据从主存复制一份到CPU的
 *      高速缓存中，那么CPU进行计算的时候就可以直接从它的高速缓存读取数据和向其中写入数据，
 *      当运算结束后再将高速缓存中的数据刷新到主存中。
 *          例如：i = i + 1。
 *          当线程执行这个语句时，会先从主存中读取i的值，然后复制一份到高速缓存当中，然后CPU执行指令i对其
 *      进行加1操作，然后将数据写入高速缓存，最后将高速缓存中的i最新的值刷新到主存中。
 *          上述代码 i = i + 1; 在单线程中运行是没有任何问题的，但是在多线程中就会有问题了，在多核CPU中，
 *      每条线程可能运行于不同CPU中，因此每个线程运行时有自己的高速缓存（对单核CPU来说，其实也会出现这种问题，
 *      只不过是以线程调度的形式来分别执行的）。比如两个线程执行这段代码，并假如i的初始值为0，那么一般情况下
 *      我们认为i最终为2。但是可能存在下面的情况：
 *          初始时，两个线程分别读取i的值存入各自所在的CPU的高速缓存中，然后线程1进行加1操作，然后把i的值1
 *      写入内存，此时线程2的高速缓存当中的值i还是0，进行加1操作之后，i的值为1，然后线程2把i的值写入到内存。
 *      所以最后的结果是1，不是2。这就是著名的缓存一致性问题。通常称这种被多个线程访问的变量为共享变量。
 *          也就是说，如果一个变量在多个CPU中都存在缓存（一般存在于多线程编程中），那么就有可能存在缓存
 *      不一致问题。
 *          为了解决缓存不一致性问题，通常来说有以下2种解决方法：
 *              1）通过在总线加LOCK#锁的方式------这是方式效率低下
 *              2）通过缓存一致性协议
 *          这两种方式都是在硬件层面上提供的方式。
 *          由于方式1会导致效率低下的问题，所以出现了缓存一致性协议。最出名的就是Intel的MESI协议，MESI协议
 *          保证了每个缓存中使用的共享变量的副本都是一致的。他的核心思想是：当CPU写数据时，如果发现操作的变量
 *          是共享变量，即在其他CPU中也存在该变量的副本，会发出信号通知其他CPU将该变量的缓存置为无效状态，
 *          因此，当其他CPU需要读取这个变量时，发现自己缓存中的缓存是无效的，那么它就会从内存重新读取。
 *
 * 二、并发编程的三个概念
 *      1.原子性
 *          原子性：即一个操作或者多个操作要么全部执行且执行的过程不会被任何因素打断，要么就不执行。
 *      2.可见性
 *          可见性是指当前多个线程访问同一个变量时，一个线程修改了这个变量的值，其他线程能够立即看得到修改的值。
 *      3.有序性
 *          有序性即程序执行的顺序按照代码的先后顺序执行。（考虑处理器的指令重排序问题）。
 *          指令重排序问题是指，处理器为了提高处理效率，会对执行指令重排序。注意，重排序时候会考虑指令之间的依赖性。
 *
 * 三、java内存模型
 *      1.原子性
 *      2.可见性  volatile
 *      3.有序性
 *
 * 四、深入剖析volatile关键字
 *      1.volatile关键字的两层语义
 *          一旦一个共享变量（类的成员变量、类的静态成员变量）被volatile修饰后，那么就具备了两层语义
 *          1）保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这个新值对其他线程来说
 *          是立即可见的。
 *          2）禁止对其指令重排序
 *      2.使用volatile关键字修饰特性
 *          1）会强制将修改的值立即写入主存
 *          2）会使其他缓存中的共享变量无效
 *          2）volatile不能保证原子性
 *          3）保证前面的已经执行，后面的还没有执行
 *      3.volatile的原理和实现机制
 *          1）“观察加入volatile关键字和没有加入volatile关键字时所生成的汇编代码发现，加入volatile关键字时，会多出一个lock前缀指令”
 *          2）它确保指令重排序时不会把其后面的指令排到内存屏障之前的位置，也不会把前面的指令排到内存屏障的后面；即在执行到内存屏障这句指令时，在它前面的操作已经全部完成；
 *          3）它会强制将对缓存的修改操作立即写入主存；
 *          4）如果是写操作，它会导致其他CPU中对应的缓存行无效。
 *      4.使用volatile关键字的场景----必须具备的两个条件
 *          1）对变量的写操作不依赖于当前值
 *          2）该变量没有包含在具有其他变量的不变式中
 *
 * Created by upnoob on 2017/12/26.
 */
public class VolatileDemo {


}
