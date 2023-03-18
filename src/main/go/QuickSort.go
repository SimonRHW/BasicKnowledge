package main

import "fmt"

func quickSort(arr []int, low, high int) {
	if low < high {
		pivot := partition(arr, low, high)
		quickSort(arr, low, pivot-1)
		quickSort(arr, pivot+1, high)
	}
}

func partition(arr []int, low, high int) int {
	pivot := arr[high]
	i := low - 1
	for j := low; j < high; j++ {
		if arr[j] < pivot {
			i++
			arr[i], arr[j] = arr[j], arr[i]
		}
	}
	arr[i+1], arr[high] = arr[high], arr[i+1]
	return i + 1
}

type Pair struct {
	left, right int
}

func quickSortStack(arr []int) {
	if len(arr) <= 1 {
		return
	}

	stack := []Pair{{0, len(arr) - 1}}

	for len(stack) > 0 {
		p := stack[len(stack)-1]
		stack = stack[:len(stack)-1]

		left, right := p.left, p.right
		if left >= right {
			continue
		}

		pivotIndex := partitionStack(arr, left, right)
		stack = append(stack, Pair{left, pivotIndex - 1})
		stack = append(stack, Pair{pivotIndex + 1, right})
	}
}

func partitionStack(arr []int, left, right int) int {
	pivot := arr[left]
	for left < right {
		for left < right && arr[right] >= pivot {
			right--
		}
		arr[left] = arr[right]
		for left < right && arr[left] <= pivot {
			left++
		}
		arr[right] = arr[left]
	}
	arr[left] = pivot
	return left
}

func main() {
	arr := []int{5, 2, 6, 1, 3, 9, 4, 8, 7}
	//quickSort(arr, 0, len(arr)-1)
	quickSortStack(arr)
	fmt.Println(arr)
}
