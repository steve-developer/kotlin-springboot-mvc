fun main() {

    //println(Solution().solution(intArrayOf(6,10,2)))

    //println(Solution().solution(intArrayOf(3,30,303)))


    val temp = mutableListOf(1,9)


    temp.sortWith(Comparator { o1, o2 ->
        println("o1 : ${o1} vs o2 : ${o2}")
        val r = o2 - o1
        println(r)

        println(temp.joinToString(""))
        r
    })

    println(temp.joinToString(""))


}


class Solution {
    fun solution(numbers: IntArray): String {


        var arrays = mutableListOf<Temp>()

        // 0,1,2,3,4,5,6,7,8,9
        for(i in 0 .. 9){
            Temp(i, mutableListOf()).run {
                arrays.add(this)
            }
        }

        for(i in numbers){
            var s = i.toString()

            if(s.length == 1){
                Number(s.toInt(),s.toInt(),s.toInt()).run {
                    arrays[s.toInt()].numbers.add(this)
                }

            }else{
                Number(s.substring(0,1).toInt(), s.substring(1,2).toInt(), s.toInt()).run {
                    arrays[first].numbers.add(this)
                }
            }
        }

        arrays.sortByDescending { it.key }

        arrays.forEach {
            it.numbers.sortWith(Comparator { first, second ->
                var f = (first.value.toString()+second.value.toString()).toInt()
                var s = (second.value.toString()+first.value.toString()).toInt()
                s-f
            })
        }


        var result = mutableListOf<String>()

        arrays.forEach {
            it.numbers?.forEach {
                result.add(it.value.toString())
            }
        }

        var count = result.filter { it == "0" }.count()
        var r = result.joinToString("")

        if(count == r.length){
            return "0"
        }

        return r
    }

}

data class Number(
        var first:Int,
        var key:Int,
        var value:Int
)

data class Temp(
        var key: Int,
        var numbers: MutableList<Number>

)