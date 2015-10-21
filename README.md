# rxjava-guide

[reactivex io](http://reactivex.io/)

一个演示响应式编程的指引示例，关注点在于为何使用响应式编程来使得我们的代码变得简洁。

注意关注`BookListFragment.java`的两个方法：

```java
    private void remoteDataWithObs(){
        IObsDummyProvider provider = new ObsRemoteDummyProvider();
        subscriptions.add(provider.createDummyItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::resetData)
                .subscribe(this::resetAdapter));
    }

    private void remoteData(){
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... params) {
                IDummyProvider provider = new RemoteDummyProvider();
                List<DummyItem> data = provider.createDummyItems();
                resetData(data);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                resetAdapter(items);
            }
        }.execute();
    }
```

他们的目的是一致的，而写法却截然不同。毫无疑问，第一个方法如此简单。

## 同步异步？

响应式的API优势还在于可以在订阅时才确定他的调度器，如果没有指定，那么将是一个同步的过程。

如果你将`subscribeOn`方法注掉，那么会看到如下的log:（意味着它在主线程运行）

```
D/rxjava-guide: createDummyItems Thread:main
```

一个延伸的用法是在组件库中，我们在设计组件库时，总是会纠结使用同步还是异步的方式调用组件的API，那么**响应式的编程方法给予更多的自由度。**

## 操作符

而响应式编程的魅力还在于它丰富的操作符。

## 平台相关

在使用AsyncTask时，我们会遇到一些麻烦，比如配置改变/页面回收/应用回收，这个Task无法控制，有时任务结束后要操作页面元素就会出现crash，所以它不是最好的选择。

