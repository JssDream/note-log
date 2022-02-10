## MyISAM 和 InnoDB 的区别

MySQL 5.5版本之前，MyISAM 引擎是 MySQL 的默认存储引擎，虽然 MyISAM 的性能还行，各种特性也还不错（比如全文索引、压缩、空间函数等）。但是，MyISAM 不支持事务和行级锁，而且最大的缺陷就是崩溃后无法安全恢复。

5.5 版本之后，MySQL 引入了 InnoDB（事务性数据库引擎），MySQL 5.5 版本后默认的存储引擎为 InnoDB。

### 区别

1. 是否支持行级锁
   MyISAM 只有表级锁(table-level locking)，而 InnoDB 支持行级锁(row-level locking)和表级锁,默认为行级锁。 也就说，MyISAM 一锁就是锁住了整张表，这在并发写的情况下是多么滴憨憨啊！这也是为什么 InnoDB 在并发写的时候，性能更牛皮了！
2. 是否支持事务
   MyISAM 不提供事务支持。 InnoDB 提供事务支持，具有提交(commit)和回滚(rollback)事务的能力。
3. 是否支持外键
   MyISAM 不支持，而 InnoDB 支持。
    1. 🌈拓展：一般我们也是不建议在数据库层面使用外键的，应用层面可以解决。不过，这样会对数据的一致性造成威胁。具体要不要使用外键还是要根据你的项目来决定。
4. 是否支持数据库异常崩溃后的安全恢复
   MyISAM 不支持，而 InnoDB 支持。 使用 InnoDB 的数据库在异常崩溃后，数据库重新启动的时候会保证数据库恢复到崩溃前的状态。这个恢复的过程依赖于 redo log 。
    1. 🌈 拓展一下：
       MySQL InnoDB 引擎使用 redo log(重做日志) 保证事务的持久性，使用 undo log(回滚日志) 来保证事务的原子性。 MySQL InnoDB 引擎通过 锁机制、MVCC 等手段来保证事务的隔离性（ 默认支持的隔离级别是 REPEATABLE-READ ）。 保证了事务的持久性、原子性、隔离性之后，一致性才能得到保障。
5. 是否支持 MVCC
   MyISAM 不支持，而 InnoDB 支持。 毕竟 MyISAM 连行级锁都不支持。 MVCC 可以看作是行级锁的一个升级，可以有效减少加锁操作，提供性能。
