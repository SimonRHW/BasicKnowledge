package main

import "fmt"

func findAverage(a []int) float64 {
	count := 4
	sum := 0
	for i := 0; i < count; i++ {
		sum += a[i]
	}

	return float64(sum / count)
}

// 定义一个函数，传入两个参数，返回一个 int 类型的值
func add(x int, y int) int {
	return x + y
}

func main() {

	var a int            // 声明一个变量 a，类型为 int
	a = 1                // 给变量 a 赋值为 1
	var b int = 2        // 声明一个变量 b，类型为 int，并给它赋值为 2
	c := 3               // 使用 := 简写方式声明变量 c，类型为 int，并给它赋值为 3
	fmt.Println(a, b, c) // 输出：1 2 3

	// 声明一个长度为 5 的数组
	var arr [5]int
	arr[0] = 1
	arr[1] = 2
	arr[2] = 3
	arr[3] = 4
	arr[4] = 5

	// 声明一个长度为 3 的切片
	slice := []int{1, 2, 3}

	fmt.Println(arr)        // 输出：[1 2 3 4 5]
	fmt.Println(slice)      // 输出：[1 2 3]
	fmt.Println(len(slice)) // 输出：3

	i := []int{4, 6, 8, 10}
	fmt.Println("AVERAGE", findAverage(i))

	// for 循环
	for i := 0; i < 5; i++ {
		fmt.Println(i)
	}

	// while 循环
	j := 0
	for j < 5 {
		fmt.Println(j)
		j++
	}

	// for range 循环
	rangeSlice := []string{"foo", "bar", "baz"}
	for index, value := range rangeSlice {
		fmt.Printf("index: %d, value: %s\n", index, value)
	}

	// 调用函数
	result := add(1, 2)
	fmt.Println(result) // 输出：3
}
