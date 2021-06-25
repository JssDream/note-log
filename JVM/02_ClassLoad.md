## 3：类加载-初始化

1. 加载过程
   1. Loading
      > loadClass(String name, boolean resolve)
          > 先去调用findLoadedClass这个方法，查看这个Class是否已经别加载，有直接返回
          > 没有进行去父类加载器，递归调用loadClass()
          > 如果父类加载器是null，说明是启动类加载器，查找对应的Class
          > 如果都没有找到，就调用findClass(String)
      > findClass(String name)
          > 根据名称或位置加载.class字节码,然后使用defineClass
          > 通常由子类去实现
      > defineClass(String name, byte[] b, int off, int len)
          > 把字节码转化为Class
      1. 双亲委派，主要出于安全来考虑  CustomClassLoader(自定义)——>AppClassLoader——>ExtClassLoader——>BootstrapClassLoader，返回去
            >为什么使用双亲委派？直接加载不好嘛
            - 主要原因安全方面考虑 【如果自定义类一个跟jvm一样的类，加载的话会被替换掉原来的】
            - 次要原因节约资源【已经加载过的类，不需要加载，直接拿来用就好了，再次加加载会资源浪费】
      2. LazyLoading 五种情况
      
         1. –new getstatic putstatic invokestatic指令，访问final变量除外
      
            –java.lang.reflect对类进行反射调用时
      
            –初始化子类的时候，父类首先初始化
      
            –虚拟机启动时，被执行的主类必须初始化
      
            –动态语言支持java.lang.invoke.MethodHandle解析的结果为REF_getstatic REF_putstatic REF_invokestatic的方法句柄时，该类必须初始化
      
      3. ClassLoader的源码
      
         1. findInCache -> parent.loadClass -> findClass()
      
      4. 自定义类加载器
      
         1. extends ClassLoader
         2. overwrite findClass() -> defineClass(byte[] -> Class clazz)
         3. 加密
         4. <font color=red>第一节课遗留问题：a.parent是如何指定的;b.打破双亲委派</font>
            a. 用super(parent)指定 系统默认是AppClassLoader，可以使用ClassLoader.getSystemClassLoader(),系统级别加载【不需要掌握】
            
            b. 双亲委派的打破
               1. 如何打破：重写loadClass（）
               2. 何时打破过？
                  1. JDK1.2之前，自定义ClassLoader都必须重写loadClass()
                  2. ThreadContextClassLoader可以实现基础类调用实现类代码，通过thread.setContextClassLoader指定
                  3. 热启动，热部署
                     1. osgi tomcat 都有自己的模块指定classloader（可以加载同一类库的不同版本）
      
      5. 混合执行 编译执行 解释执行
      
         1. 检测热点代码：-XX:CompileThreshold = 10000
      
   2. Linking 
      1. Verification
         1. 验证文件是否符合JVM规定
      2. Preparation
         1. 静态成员变量赋默认值
      3. Resolution
         1. 将类、方法、属性等符号引用解析为直接引用
            常量池中的各种符号引用解析为指针、偏移量等内存地址的直接引用
      
   3. Initializing
   
      1. 调用类初始化代码 <clinit>，给静态成员变量赋初始值
   
2. 小总结：

   1. load - 默认值 - 初始值
   2. new - 申请内存 - 默认值 - 初始值
