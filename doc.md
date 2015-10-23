# reactive x 完整参考

## Operators

### Creating Observables

用于创建Observables

#### Create - 手动创建一个Observable，通过调用observer的方法来实现

![](https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/create.png)

```java
    private Observable<String> buildObservableByCreate(boolean hasError) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    subscriber.onNext("1");
                    subscriber.onNext("2");
                    if (hasError) {
                        emitsError();
                    }
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    @Test
    public void testCreate() throws Exception {
        Logger.log("testCreate");
        final boolean hasError = false;

        Observable<String> obs = buildObservableByCreate(hasError);
        obs.subscribe(
                s -> Logger.log("onNext - " + s),
                e -> {
                    Logger.log("onError");
                    assertTrue(hasError);
                },
                () -> {
                    Logger.log("onCompleted");
                    assertFalse(hasError);
                });
    }
```

#### Defer - 延迟创建Observable，直到有观察者订阅后，才会创建真正的Observable

```java
    @Test
    public void testDeferOperator() throws Exception {
        Logger.log("testDeferOperator");
        Observable<String> obs = Observable.defer(() -> Observable.just("1", "2"));
        obs.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(Logger::log);
    }
```

#### Empty - 只带有结束事件的Observable
#### Never - 没有任何事件的Observable（不终止）
#### **Throw** - 只带有error事件的Observable# reactive x 完整参考


                                      ## Operators

                                      ### Creating Observables

                                      用于创建Observables

                                      #### Create - 手动创建一个Observable，通过调用observer的方法来实现

                                      ![](https://raw.github.com/wiki/ReactiveX/RxJava/images/rx-operators/create.png)

                                      ```java
                                          private Observable<String> buildObservableByCreate(boolean hasError) {
                                              return Observable.create(new Observable.OnSubscribe<String>() {
                                                  @Override
                                                  public void call(Subscriber<? super String> subscriber) {
                                                      try {
                                                          subscriber.onNext("1");
                                                          subscriber.onNext("2");
                                                          if (hasError) {
                                                              emitsError();
                                                          }
                                                          subscriber.onCompleted();
                                                      } catch (Exception e) {
                                                          subscriber.onError(e);
                                                      }
                                                  }
                                              });
                                          }

                                          @Test
                                          public void testCreate() throws Exception {
                                              Logger.log("testCreate");
                                              final boolean hasError = false;

                                              Observable<String> obs = buildObservableByCreate(hasError);
                                              obs.subscribe(
                                                      s -> Logger.log("onNext - " + s),
                                                      e -> {
                                                          Logger.log("onError");
                                                          assertTrue(hasError);
                                                      },
                                                      () -> {
                                                          Logger.log("onCompleted");
                                                          assertFalse(hasError);
                                                      });
                                          }
                                      ```

                                      #### Defer - 延迟创建Observable，直到有观察者订阅后，才会创建真正的Observable

                                      ```java
                                          @Test
                                          public void testDeferOperator() throws Exception {
                                              Logger.log("testDeferOperator");
                                              Observable<String> obs = Observable.defer(() -> Observable.just("1", "2"));
                                              obs.subscribeOn(Schedulers.io())
                                                      .observeOn(Schedulers.newThread())
                                                      .subscribe(Logger::log);
                                          }
                                      ```

                                      #### Empty - 只带有结束事件的Observable
                                      #### Never - 没有任何事件的Observable（不终止）
                                      #### **Throw** - 只带有error事件的Observable
                                      #### **From** - 从一个数据集合（迭代器）转为逐个的值事件
                                      ```java
                                          @Test
                                          public void testFromOperator() throws Exception {
                                              Logger.log("testFromOperator");
                                              List<String> data = Arrays.asList("1", "2", "3");
                                              Observable.from(data)
                                                      .subscribe(Logger::log);
                                          }
                                      ```

                                      ![](http://reactivex.io/documentation/operators/images/from.c.png)
                                      * **Interval** - 定时产生事件，值为次序
                                      ![](http://reactivex.io/documentation/operators/images/interval.c.png)
                                      * **Just** 将对象或者对象集合转换为一个会发射这些对象的Observable
                                      * **Range** - 创建一个范围内的连续整数值事件的Observable
                                      ![](http://reactivex.io/documentation/operators/images/range.c.png)
                                      * **Repeat** 把一个Observable的值事件重复多次，成为新的Observable
                                      ![](http://reactivex.io/documentation/operators/images/repeat.o.png)
                                      *
#### **From** - 从一个数据集合（迭代器）转为逐个的值事件
```java
    @Test
    public void testFromOperator() throws Exception {
        Logger.log("testFromOperator");
        List<String> data = Arrays.asList("1", "2", "3");
        Observable.from(data)
                .subscribe(Logger::log);
    }
```

![](http://reactivex.io/documentation/operators/images/from.c.png)
* **Interval** - 定时产生事件，值为次序
![](http://reactivex.io/documentation/operators/images/interval.c.png)
* **Just** 将对象或者对象集合转换为一个会发射这些对象的Observable
* **Range** - 创建一个范围内的连续整数值事件的Observable
![](http://reactivex.io/documentation/operators/images/range.c.png)
* **Repeat** 把一个Observable的值事件重复多次，成为新的Observable
![](http://reactivex.io/documentation/operators/images/repeat.o.png)
*