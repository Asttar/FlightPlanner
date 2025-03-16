<template>
    <button @click="backToMain()">Back to main page</button>
    <div>

        <div class="flight_info" v-if="flight">
            <p>Flight to <strong>{{ flight.destination }}</strong></p>
            <p>At <strong>{{ flight.date }} , {{ flight.time }}</strong></p>
            <p>With a price of <strong>{{ flight.price }} €</strong></p>
        </div>



        <div class="container">

            <div class="filters">
                <p class="green">Availible seats</p>
                <p class="yellow">Chosen seats</p>
                <p class="red">Taken seats</p>
                <div>
                    <label for="count_of_tickets">Number of tickets </label>
                    <select id="count_of_tickets" @click="this.fromDropDownMenu = true" v-model="desiredTicketsCount">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                    </select>
                </div>

                <div>
                    <label for="window">Window seat</label>
                    <input id="window" type="checkbox" name="Window seat" value="Window seat" v-model="window"
                        @click="this.fromDropDownMenu = true">
                </div>

                <div>
                    <label for="legroom">More legroom</label>
                    <input id="legroom" type="checkbox" name="More legroom" value="More legroom" v-model="legroom"
                        @click="this.fromDropDownMenu = true">
                </div>
                <div>
                    <label for="exit">Close to the exit</label>
                    <input id="exit" type="checkbox" name="Close to the exit" value="Close to the exit" v-model="exit"
                        @click="this.fromDropDownMenu = true">
                </div>
                <div>
                    <label for="side">Side-by-side seats (pairs)</label>
                    <input id="side" type="checkbox" name="Side-by-side seats" value="Side-by-side seats" v-model="side"
                        @click="this.fromDropDownMenu = true">
                </div>
            </div>



            <div class="plane-image-wrapper">
                <img class="plane-image" src="../assets/plane.svg" alt="Plane">

                <div v-for="(seat, index) in seats1" :key="index" @click="toggleChoose(seat)" :class="seat[3]"
                    :style="{ top: seat[0] + '%', left: seat[1] + '%' }">{{ seat[2] }}</div>
                <div v-for="(seat, index) in seats2" :key="index" @click="toggleChoose(seat)" :class="seat[3]"
                    :style="{ top: seat[0] + '%', left: seat[1] + '%' }">{{ seat[2] }}</div>

                <div v-for="(seat, index) in seats3" :key="index" @click="toggleChoose(seat)" :class="seat[3]"
                    :style="{ top: seat[0] + '%', left: seat[1] + '%' }">{{ seat[2] }}</div>
                <div v-for="(seat, index) in seats4" :key="index" @click="toggleChoose(seat)" :class="seat[3]"
                    :style="{ top: seat[0] + '%', left: seat[1] + '%' }">{{ seat[2] }}</div>
                <div v-for="(seat, index) in seats5" :key="index" @click="toggleChoose(seat)" :class="seat[3]"
                    :style="{ top: seat[0] + '%', left: seat[1] + '%' }">{{ seat[2] }}</div>

            </div>

        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            flight: null,
            flightId: this.$route.params.id,  
            seats1: [],
            seats2: [],
            seats3: [],
            seats4: [],
            seats5: [],
            desiredTicketsCount: 1,
            selectedSeats: [],
            window: false,
            legroom: false,
            exit: false,
            side: false,
            fromDropDownMenu: true,
        };
    },
    watch: {
        desiredTicketsCount(newCount) {
            if (this.fromDropDownMenu) {
                this.assignSeats(newCount);
            }
        },
        window() {
            if (this.fromDropDownMenu) this.assignSeats();
        },
        legroom() {
            if (this.fromDropDownMenu) this.assignSeats();
        },
        exit() {
            if (this.fromDropDownMenu) this.assignSeats();
        },
        side() {
            if (this.fromDropDownMenu) this.assignSeats();
        }

    },
    mounted() {
        fetch(`http://localhost:3000/api/flights/${this.$route.params.id}`)
            .then((response) => response.json())
            .then((data) => {
                this.flight = data;
            })
            .catch(() => {
                console.error("Error loading flight");
                this.$router.push("/");
            });

        this.seats1 = this.positionseats1();
        this.seats2 = this.positionseats2();
        this.seats3 = this.positionseats3();
        this.seats4 = this.positionseats4();
        this.seats5 = this.positionseats5();
        this.assignSeats(this.desiredTicketsCount);
    },
    methods: {
        backToMain() {
            this.$router.push(`/`);
        },
        assignSeats() {
            let tickets = this.desiredTicketsCount
            let allSeats = [...this.seats1, ...this.seats2, ...this.seats3, ...this.seats4, ...this.seats5]
            for (let seat = 0; seat < allSeats.length; seat++) {
                if (allSeats[seat][3] == "seat_chosen") {
                    allSeats[seat][3] = "seat_availible"
                }
            }
            if (this.window) {
                allSeats = allSeats.filter(seat => seat[2].charAt(seat[2].length - 1) == 'A' || seat[2].charAt(seat[2].length - 1) == 'D')
            }
            if (this.legroom) {
                allSeats = allSeats.filter(seat => parseInt(seat[2].substring(0, seat[2].length - 1)) >= 1 && parseInt(seat[2].substring(0, seat[2].length - 1)) <= 7 || parseInt(seat[2].substring(0, seat[2].length - 1)) == 13 || parseInt(seat[2].substring(0, seat[2].length - 1)) == 14)
            }
            if (this.exit) {
                allSeats = allSeats.filter(seat => parseInt(seat[2].substring(0, seat[2].length - 1)) >= 12 && parseInt(seat[2].substring(0, seat[2].length - 1)) <= 14 || parseInt(seat[2].substring(0, seat[2].length - 1)) == 1 || parseInt(seat[2].substring(0, seat[2].length - 1)) == 33)
            }

            if (this.side) {
                let seatsNextToEachOther = ["AB", "CD"]
                for (let seat = 0; seat < allSeats.length - 1; seat++) {
                    if (tickets == 1) {
                        break
                    }
                    if (allSeats[seat][2].substring(0, allSeats[seat][2].length - 1) == allSeats[seat + 1][2].substring(0, allSeats[seat + 1][2].length - 1)
                        && seatsNextToEachOther.includes(allSeats[seat][2].charAt(allSeats[seat][2].length - 1) + allSeats[seat + 1][2].charAt(allSeats[seat + 1][2].length - 1))
                        && allSeats[seat][3] == "seat_availible"
                        && allSeats[seat + 1][3] == "seat_availible") {
                        allSeats[seat][3] = "seat_chosen"
                        allSeats[seat + 1][3] = "seat_chosen"
                        tickets -= 2;
                    }
                    if (tickets == 0) {
                        return
                    }
                }
            }
            for (let seat = 0; seat < allSeats.length; seat++) {
                if (allSeats[seat][3] == "seat_availible") {
                    allSeats[seat][3] = "seat_chosen"
                    tickets -= 1;
                }
                if (tickets == 0) {
                    return
                }
            }
            if (tickets != 0) {
                alert("There aren't enough availible seats fitting the current filters. Please change the amount of tickets or filters")
                this.$nextTick(() => {
                    this.window = false;
                    this.legroom = false;
                    this.exit = false;
                    this.side = false;
                    this.desiredTicketsCount = 1;
                });
            }
        },
        positionseats1() {
            let rows_and_seats = []
            let top = 15.94
            let letters = ['A', 'B', 'C', 'D']
            for (let row = 1; row < 5; row++) {
                let left = 40.6
                for (let left_seat = 0; left_seat < 2; left_seat++) {
                    let row_and_seat = [top, left, row + letters[left_seat], this.seatRandom()]
                    rows_and_seats.push(row_and_seat)
                    left += 3.91
                }
                left = 52.3
                for (let right_seat = 2; right_seat < 4; right_seat++) {
                    let row_and_seat = [top, left, row + letters[right_seat], this.seatRandom()]
                    rows_and_seats.push(row_and_seat)
                    left += 3.9
                }
                top += 2.64
            }
            return rows_and_seats
        },
        positionseats2() {
            let rows_and_seats = []
            let top = 26.34
            let letters = ['A', 'B', 'C', 'D']
            for (let row = 5; row < 8; row++) {
                let left = 40.6
                for (let left_seat = 0; left_seat < 2; left_seat++) {
                    let row_and_seat = [top, left, row + letters[left_seat], this.seatRandom()]
                    rows_and_seats.push(row_and_seat)
                    left += 3.91
                }
                left = 52.3
                for (let right_seat = 2; right_seat < 4; right_seat++) {
                    let row_and_seat = [top, left, row + letters[right_seat], this.seatRandom()]
                    rows_and_seats.push(row_and_seat)
                    left += 3.9
                }
                top += 2.55
            }
            return rows_and_seats
        },
        positionseats3() {
            let rows_and_seats = []
            let top = 33.65
            let letters = ['A', 'B', 'C', 'D']
            for (let row = 8; row < 13; row++) {
                let left = 40.6
                for (let left_seat = 0; left_seat < 2; left_seat++) {
                    let row_and_seat = [top, left, row + letters[left_seat], this.seatRandom()]
                    rows_and_seats.push(row_and_seat)
                    left += 3.9
                }
                left = 52.3
                for (let right_seat = 2; right_seat < 4; right_seat++) {
                    let row_and_seat = [top, left, row + letters[right_seat], this.seatRandom()]
                    rows_and_seats.push(row_and_seat)
                    left += 3.9
                }
                top += 2.25
            }
            return rows_and_seats
        },
        positionseats4() {
            let rows_and_seats = [
                [46.02, 40.6, "13A", this.seatRandom()],
                [46.02, 44.5, "13B", this.seatRandom()],
                [46.02, 52.3, "13C", this.seatRandom()],
                [46.02, 56.2, "13D", this.seatRandom()]
            ]
            return rows_and_seats
        },
        positionseats5() {
            let rows_and_seats = []
            let top = 49.38
            let letters = ['A', 'B', 'C', 'D']
            for (let row = 14; row < 34; row++) {
                let left = 40.6
                for (let left_seat = 0; left_seat < 2; left_seat++) {
                    let row_and_seat = [top, left, row + letters[left_seat], this.seatRandom()]
                    rows_and_seats.push(row_and_seat)
                    left += 3.9
                }
                left = 52.3
                for (let right_seat = 2; right_seat < 4; right_seat++) {
                    let row_and_seat = [top, left, row + letters[right_seat], this.seatRandom()]
                    rows_and_seats.push(row_and_seat)
                    left += 3.9
                }
                top += 2.24
            }
            return rows_and_seats
        },
        toggleChoose(seat) {
            if (seat[3] === "seat_taken") {
                return
            }
            this.window = false
            this.legroom = false
            this.exit = false
            this.side = false
            this.fromDropDownMenu = false
            if (seat[3] === "seat_availible") {
                this.desiredTicketsCount++
                seat[3] = "seat_chosen";
            }
            else if (seat[3] === "seat_chosen") {
                this.desiredTicketsCount--
                seat[3] = "seat_availible";
            }

            if (this.desiredTicketsCount > 10) {
                this.fromDropDownMenu = true
                alert("Can't buy more than 10 tickets at once!")
                this.$nextTick(() => {
                    this.window = false;
                    this.legroom = false;
                    this.exit = false;
                    this.side = false;
                    this.desiredTicketsCount = 1;
                });
            }
        },
        seatRandom() {
            let chance = Math.floor(Math.random() * 10);
            if (chance <= 2) {
                return "seat_taken"
            }
            return "seat_availible"
        }
    }

};
</script>

<style scoped>
.plane-image-wrapper {
    display: inline-block;
    position: relative;
}

.plane-image {
    display: block;
    width: 100%;
    height: auto;
}

.seat_taken,
.seat_availible,
.seat_chosen {
    position: absolute;
    width: 3.35%;
    height: 2.05%;
    text-align: center;
    display: flex;
    align-items: center;
    justify-content: center;
    color: white;
    font-size: 1vw;
    ;
    border-radius: 20%;
    z-index: 2;
}

.seat_taken {
    background-color: #D32F2F;
}

.seat_availible {
    background-color: #388E3C;
}

.seat_chosen {
    background-color: #FBC02D;
}

.seat_taken:hover {
    cursor: not-allowed;
}

.seat_availible:hover {
    background-color: #48b04d;
    cursor: pointer;
}

.seat_chosen:hover {
    background-color: #fbd064;
    cursor: pointer;
}

.container {
    justify-content: space-between;
    display: flex;
}

.filters {
    padding: 10px;
    background-color: #007bff;
    display: flex;
    flex-direction: column;
    border-radius: 10px;
    min-width: 205px;
}

.filters>div {
    margin-bottom: 50px;
}

label {
    font-size: 20px;
    font-weight: bold;
    color: rgb(255, 255, 255);
    margin-right: 10px;
}

input[type="checkbox"] {
    transform: scale(2);
}

select {
    width: 70px;
}

.flight_info {
    width: 250px;
    margin: auto;
}

p {
    font-size: 20px;
}

input:hover {
    cursor: pointer;
}

select:hover {
    cursor: pointer;
}

.red {
    max-width: 200px;
    color: rgb(62, 60, 60);
    background-color: #D32F2F;
}

.yellow {
    max-width: 200px;
    color: rgb(62, 60, 60);
    background-color: #FBC02D;
}

.green {
    max-width: 200px;
    color: rgb(62, 60, 60);
    background-color: #388E3C;
}

button {
    border-radius: 8px;
    border: 1px solid transparent;
    padding: 0.6em 1.2em;
    font-size: 1em;
    font-weight: 500;
    font-family: inherit;
    cursor: pointer;
    transition: border-color 0.25s;
}

button:hover {
    border-color: #646cff;
}

@media (max-width: 800px) {
    .container {
        flex-direction: column;
    }
}
</style>