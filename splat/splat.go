package main

import (
	"fmt"
	"math"
)

func main() {
	var paint_n int64
	fmt.Scanln(&paint_n)

	for i := 0; i < int(paint_n); i++ {
		var points_n int64
		fmt.Scanln(&points_n)

		points := []Point{}

		for j := 0; j < int(points_n); j++ {
			var x, y, v float64
			var color string
			fmt.Scanln(&x, &y, &v, &color)

			p := Point{x, y, v, color}
			points = append(points, p)
		}

		// Queries
		var queries_n int64
		fmt.Scanln(&queries_n)

		for k := 0; k < int(queries_n); k++ {
			var x, y float64
			fmt.Scanln(&x, &y)

			c := process_query(x, y, points)
			fmt.Printf("%s\n", c)
		}
	}
}

func process_query(x float64, y float64, points []Point) string {
	for i := range points {
		point := points[len(points)-1-i]
		x_d := math.Pow(x-point.X, 2)
		y_d := math.Pow(y-point.Y, 2)
		if math.Sqrt(x_d+y_d) <= math.Sqrt(point.V/math.Pi) {
			return point.Color
		}
	}
	return "white"
}

type Point struct {
	X     float64
	Y     float64
	V     float64
	Color string
}
