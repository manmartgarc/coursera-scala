class HelloThread extends Thread:
  override def run() =
    println("Hello ")
    println("world!")

def main() =
  val t = HelloThread()
  val s = HelloThread()
  t.start()
  s.start()
  t.join()
  s.join()

main()

object notAtomic:
  private var uidCount = 0L
  def getUniqueId(): Long =
    uidCount += 1
    uidCount

def startThread() =
  val t = new Thread {
    override def run() =
      val uids = for i <- 0 until 10 yield notAtomic.getUniqueId()
      println(uids)
  }
  t.start()
  t

startThread()

object Sync:
  private val x = new AnyRef {}
  private var uidCount = 0L
  def getUniqueId(): Long = x.synchronized {
    uidCount += 1
    uidCount
  }

def startThreadSync() =
  val t = new Thread {
    override def run() =
      val uids = for i <- 0 until 10 yield Sync.getUniqueId()
      println(uids)
  }
  t.start()
  t

startThreadSync()
