package main

import "fmt"
import "bufio"
import "os"
import "sort"
import "strconv"

func main() {
	var ss = make([]int, 0, 3)

	var s string

	for {
		fmt.Println("Please input an integer:")
		scanner := bufio.NewScanner(os.Stdin)

		scanner.Scan()
		s = scanner.Text()
		if err := scanner.Err(); err != nil {
			os.Exit(1)
		}
		if (s == "X") {
			fmt.Println("Your input is X, exiting...")
			break
		}

		var num, err = strconv.Atoi(s)

		if (err != nil) {

			fmt.Println("You should either input an integer, or X indicating exit")

		} else {

			ss = append(ss, num)

			sort.Ints(ss)
			fmt.Printf("%v\n", ss)

		}

	}

}
