fun main(args: Array<String>) {
    var item1 = Item("item1", 10, 10)
    var item2 = Item("item2", 10, -1)
    println(item1.toString())
    println(item2.toString())
    item1.buy()
    println(item1.toString())
    item1.buy2(90)
    println(item1.toString())
}

class Item(
    var itemName: String,
    private var price: Int = 0,
    private var count: Int = 0,
) {
    init {
        if (count < 0) {
            println("에러")
            count = 0
        }
    }

    override fun toString(): String {
        return "$itemName $price $count"
    }

    fun buy() {
        count--
    }

    fun buy2(cnt: Int) {
        if (count < cnt) {
            println("재고 부족")
        } else {
            count -= cnt
        }
    }
}