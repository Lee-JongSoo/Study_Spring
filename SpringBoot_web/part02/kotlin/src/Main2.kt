fun main(args: Array<String>) {

    // val - 읽기용
    // var -  읽기쓰기
    // ? - nullable

    var a : Any? = 123
    a = null

    var c: String? = null
    val length = c?.length ?: 0
    println("length = $length")

    var b: String = "1"
    b.length

}