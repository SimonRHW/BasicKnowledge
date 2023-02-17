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

func main() {
	i := []int{4, 6, 8, 10}
	fmt.Println("AVERAGE", findAverage(i))
}
