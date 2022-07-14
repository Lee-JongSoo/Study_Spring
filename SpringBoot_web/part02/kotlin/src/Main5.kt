//fun main(args: Array<String>) {
//    val iphone = Product("item", 10000, 0)
//    println(iphone)
//
//    val default = Product("default")
//    println(default)
//
//    val minusPrice = Product("minus", -1000)
//    println(minusPrice)
//}
//
//class Product2(
//    val name: String,
//    private var price: Int = 0,
//    private var amount: Int = 0
//){
//    init {
//        if (price < 0) {
//            println("error")
//            price = 0
//        }
//
//        if (amount < 0) {
//            println("error")
//            price = 0
//        }
//    }
//
//    fun buy(want: Int = 1) {
//        this.amount -= want
//    }
//
//    override fun toString(): String {
//        return "Product(name='$name', price=$price, amount=$amount)"
//    }
//
//
//}