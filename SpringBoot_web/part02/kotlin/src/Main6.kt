fun main(args: Array<String>) {
    val pair = Pair<String, String>("key", "value")
    val infixVerPair = "key" to "value"
    println(pair)
    println(infixVerPair)
    println(1 plus 2)

    var str: String? = "test"
//    println(str.uppercase())

    var num:Int? = null
    println()
}

// infix 중위 함수
infix fun Int.plus(a: Int) = this + a

// extension 확장 함수
//fun String?.uppercase():String = this?.uppercase() ?: "It is null"
fun Int?.orZero(): Int = this ?: 0



//fun updatePrice(price: Int){
//    this.apply {
//        this.price = 10
//    }
//}