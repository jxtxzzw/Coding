package main

import (
	"fmt"
	"encoding/json"
 	"bufio"
 	"os"
)

func main() {

	m := make(map[string]string)

	scanner := bufio.NewScanner(os.Stdin)

	fmt.Println("Please input your name:")
	scanner.Scan()
	m["name"] = scanner.Text()
	if err := scanner.Err(); err != nil {
		os.Exit(1)
	}

	fmt.Println("Please input your address:")
	scanner.Scan()
	m["address"] = scanner.Text()
	if err := scanner.Err(); err != nil {
		os.Exit(1)
	}

	jsonString, _ := json.Marshal(m)

	fmt.Println(string(jsonString))


}
