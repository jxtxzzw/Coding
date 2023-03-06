package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

var predefinedAnimals []string = []string{"cow", "bird", "snake"}
var predefinedInformation []string = []string{"eat", "move", "speak"}
var predefinedPrompt string = "Invalid Input!"
var predefinedCommand []string = []string{"newanimal", "query"}

func validateInput(ss []string) bool {
	correctNum := len(ss) == 3
	correctAnimal := false
	correctInfo := false
	correctCommand := false
	if correctNum {
		for _, v := range predefinedCommand {
			if v == ss[0] {
				correctCommand = true
			}
		}
		if correctCommand {
			if ss[0] == predefinedCommand[0] {
				for _, v := range predefinedAnimals {
					if v == ss[2] {
						correctAnimal = true
					}
				}
				return correctAnimal
			} else {
				for _, v := range predefinedInformation {
					if v == ss[2] {
						correctInfo = true
					}
				}
				return correctInfo
			}
		}
	}
	return false
}

type Animal interface {
	Eat() string
	Move() string
	Speak() string
}

type Cow struct {
	eat   string
	move  string
	speak string
}

type Bird struct {
	eat   string
	move  string
	speak string
}

type Snake struct {
	eat   string
	move  string
	speak string
}

func (cow *Cow) Eat() string {
	return cow.eat
}

func (cow *Cow) Move() string {
	return cow.move
}

func (cow *Cow) Speak() string {
	return cow.speak
}

func (bird *Bird) Eat() string {
	return bird.eat
}

func (bird *Bird) Move() string {
	return bird.move
}

func (bird *Bird) Speak() string {
	return bird.speak
}

func (snake *Snake) Eat() string {
	return snake.eat
}

func (snake *Snake) Move() string {
	return snake.move
}

func (snake *Snake) Speak() string {
	return snake.speak
}

func main() {
	animals := make(map[string]Animal)

	scanner := bufio.NewScanner(os.Stdin)

	for {
		fmt.Print("> ")

		scanner.Scan()
		s := scanner.Text()
		if err := scanner.Err(); err != nil {
			fmt.Printf("%s\n", predefinedPrompt)
			os.Exit(1)
		}

		ss := strings.Split(s, " ")

		if validateInput(ss) {
			command := ss[0]
			name := ss[1]
			switch command {
			case "newanimal":
				var animal Animal
				switch ss[2] {
				case "cow":
					animal = &Cow{eat: "grass", move: "walk", speak: "moo"}
				case "bird":
					animal = &Bird{eat: "worms", move: "fly", speak: "peep"}
				case "snake":
					animal = &Snake{eat: "mice", move: "slither", speak: "hsss"}
				}
				animals[name] = animal
				fmt.Println("Created it!")
			case "query":
				animal := animals[name]
				if animal == nil {
					fmt.Printf("%s\n", predefinedPrompt)
				} else {
					switch ss[2] {
					case "eat":
						fmt.Println(animal.Eat())
					case "move":
						fmt.Println(animal.Move())
					case "speak":
						fmt.Println(animal.Speak())
					}
				}
			}
		} else {
			fmt.Printf("%s\n", predefinedPrompt)
		}

	}
}
