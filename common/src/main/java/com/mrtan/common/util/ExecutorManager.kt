package com.mrtan.common.util

import java.io.File
import java.io.FileFilter
import java.util.concurrent.BlockingQueue
import java.util.concurrent.ExecutorService
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.RejectedExecutionHandler
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

internal object ExecutorManager {

  val DEVICE_INFO_UNKNOWN = 0
  val eventExecutor: ExecutorService
  val CPU_COUNT = ExecutorManager.countOfCPU
  val CORE_POOL_SIZE = CPU_COUNT + 1
  val MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1
  val KEEP_ALIVE = 1
  val eventPoolWaitQueue: BlockingQueue<Runnable> = LinkedBlockingQueue(128)
  val eventThreadFactory: ThreadFactory = object : ThreadFactory {
    private val mCount = AtomicInteger(1)

    override fun newThread(r: Runnable): Thread {
      return Thread(r, "eventAsyncAndBackground #" + mCount.getAndIncrement())
    }
  }

  val eventHandler: RejectedExecutionHandler = ThreadPoolExecutor.CallerRunsPolicy()

  init {
    eventExecutor = ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE.toLong(),
        TimeUnit.SECONDS,
        eventPoolWaitQueue, eventThreadFactory, eventHandler)
  }

  /**
   * Linux中的设备都是以文件的形式存在，CPU也不例外，因此CPU的文件个数就等价与核数。
   * Android的CPU 设备文件位于/sys/devices/system/cpu/目录，文件名的的格式为cpu\d+。

   * 引用：http://www.jianshu.com/p/f7add443cd32#，感谢 liangfeizc :)
   * https://github.com/facebook/device-year-class
   */
  val countOfCPU: Int
    get() {
      var count: Int
      try {
        count = File("/sys/devices/system/cpu/").listFiles(CPU_FILTER).size
      } catch (e: SecurityException) {
        count = DEVICE_INFO_UNKNOWN
      } catch (e: NullPointerException) {
        count = DEVICE_INFO_UNKNOWN
      }

      return count
    }

  private val CPU_FILTER = FileFilter { pathname ->
    val path = pathname.name
    if (path.startsWith("cpu")) {
      for (i in 3..path.length - 1) {
        if (path[i] < '0' || path[i] > '9') {
          return@FileFilter false
        }
      }
      return@FileFilter true
    }
    false
  }
}