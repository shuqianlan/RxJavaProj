
#RxJava


- 官方 https://mcxiaoke.gitbooks.io/rxdocs/content/
> 简洁

## 默认规则
```
#在不指定线程的情况下，RxJava遵循的是线程不变的原则。即：在哪个线程调用 subscribe()，就在哪个线程生产事件；在哪个线程生产事件，就在哪个线程消费事件。如果需要切换线程，就需要用到 Scheduler （调度器）。

``` 
- https://blog.csdn.net/xx326664162/article/details/52068014 (RxJava讲解)

## 观察者模式

- Observable  被观察者
- Observer    观察者
- subscribe   订阅
- unsubscribe 取消订阅
### 事件回调方法

#### Observer -- 观察者

- onNext()
- onComplete()； 事件队列完结
- onError(); 事件队列异常

#### Subscriber -- 签署者

## 切换线程 -- Scheduler(调度器)

- Scheduler.immediate();   // 当前线程中执行
- Scheduler.newThread();   // 总是启用新线程
- Scheduler.io;            // IO操作(读写文件/数据库/网络信息交互)，其内部是无上限的线程池
- Scheduler.computation(); // CPU密集型计算
- AndroidSchedulers.mainThread() // Android主线程

- subscribeOn : 指定Observerable.OnSubscribe被激活时锁处的线程
- observerOn  : 指定Subscriber所运行的线程.

## Scheduler原理

### 变换

> 将事件序列中的对象或整个序列进行加工处理，转换成不同的事件或事件序列。

- map     事件对象的直接变换
- faltMap 
- lift
- compose
- throttleFirst 在每次事件触发后的一定时间间隔内丢弃新的事件
 
## Retrofit + RxJava