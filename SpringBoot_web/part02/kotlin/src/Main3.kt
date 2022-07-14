import java.lang.Exception

fun main(args: Array<String>) {
    val person = Person("lee", 26)
    val person2 = Person("lee", 26)


    println(person.toString())
//    println("person.age = ${person.age}")
//    println("person.name = ${person.name}")

}

class Person(
    val name: String,
    private var age: Int
) {

    fun getOlder(passYear: Int = 1) {

    }

    init {
        if (age < 0) {
            throw Exception("나이는 음수가 될 수 없다.")
        }
        println("생성 되었습니다.")
    }

    override fun toString(): String {
        return "$name $age"
    }
}