import java.nio.ByteBuffer

object SHA1 {
  def main(args: Array[String]): Unit = {
    val hello: String = "Helloooooooooooooooooo"
    val expandHello: List[Byte] = padding(hello)
    println(expandHello)
  }

  val paddingBlock  : Byte = 64
  val firstPadding  : Byte = 1
  val fillingPadding: Byte = 0
  val originMsgLen  : Byte = 8

  def padding(str: String): List[Byte] = {
    val strByteList   : List[Byte] = str.getBytes().toList
    val lastBytesLen  : Integer    = strByteList.length % paddingBlock

    val remain = if(lastBytesLen < paddingBlock - originMsgLen) {
        paddingBlock - originMsgLen - lastBytesLen
      } else {
        (paddingBlock - lastBytesLen) + (paddingBlock - originMsgLen)
      }

    val firstPad      : List[Byte] = List[Byte](firstPadding)
    val secondPad     : List[Byte] = List.fill(remain - 1)(fillingPadding)
    val originStrLen  : List[Byte] = ByteBuffer.allocate(originMsgLen).putLong(strByteList.length).array().toList
    return strByteList ::: firstPad ::: secondPad ::: originStrLen
  }
}
