<template>
  <div class="card">
    <label for="destination">Please enter your destination</label>
    <select id="destination" v-model="search.destination">
      <option value="">Select a destination</option>
      <option v-for="destination in uniqueDestinations">{{ destination }}</option>
    </select>

    <label for="date">Please enter the desired date and time for your flight</label>
    <div class="date_and_time" id="date_and_time">
      <input type="date" id="date" v-model="search.date">
      <input type="time" id="time" v-model="search.time">
    </div>


    <label for="price_min">Please enter the price range for your flight</label>
    <div class="side-by-side" id="side-by-side">
      <input class="price" type="number" id="price_min" v-model.number="search.price_min">
      <p>€</p>
      <p class="hyphen">-</p>
      <input class="price" type="number" id="price_max" v-model.number="search.price_max">
      <p>€</p>
    </div>


    <button type="button" @click="fetchFlightsByFilter()">Search flights</button>
  </div>
  <div class="sorting">
    <label class="sorting-label text" for="sort">Sort flights by:</label>
    <select id="sort" v-model="desiredSorting">
      <option value="price_asc">Price (Low to High)</option>
      <option value="price_desc">Price (High to Low)</option>
      <option value="date_asc">Date (Earliest to Latest)</option>
      <option value="date_desc">Date (Latest to Earliest)</option>
      <option value="alphabetical_asc">Destination (A-Z)</option>
      <option value="alphabetical_desc">Destination (Z-A)</option>
    </select>
  </div>


  <div class="amount_of_flights text"><b>Found {{ sortedFlights.length }} flight(s).</b></div>
  <ul>
    <li class="item" v-for="flight in sortedFlights" :key="flight.id">
      <div class="flight" @click="navigateToFlight(flight.id)">
        <img :src="flight.image" alt="Travel picture">
        <div class="flight_details">
          <span><b>{{ flight.destination }}</b></span>
          <span>{{ flight.date }}</span>
          <span>{{ flight.time }}</span>
          <span>{{ flight.price }}€</span>
        </div>
      </div>
    </li>
  </ul>
</template>

<script>
export default {
  data() {
    return {
      search: {
        destination: "",
        date: "",
        time: "",
        price_min: 0,
        price_max: 1000,
      },
      output: "",
      flights: [],
      uniqueDestinations: [],
      desiredSorting: "alphabetical_asc"
    };
  },
  computed: {
    sortedFlights() {
      let sorted = [...this.flights];
      switch (this.desiredSorting) {
        case "date_asc":
          return sorted.sort((a, b) => (new Date(a.date) - new Date(b.date)))

        case "date_desc":
          return sorted.sort((a, b) => (new Date(b.date) - new Date(a.date)))

        case "price_asc":
          return sorted.sort((a, b) => a.price - b.price)

        case "price_desc":
          return sorted.sort((a, b) => b.price - a.price)

        case "alphabetical_asc":
          return sorted.sort((a, b) => a.destination.localeCompare(b.destination))

        case "alphabetical_desc":
          return sorted.sort((a, b) => b.destination.localeCompare(a.destination))
      }
    }
  },
  mounted() {
    // Fetch the JSON file when the component is loaded
    fetch('http://localhost:3000/api/flights/')
      .then((response) => response.json())
      .then((data) => {
        this.flights = data; // Save flights to data()
        for (let flight of this.flights) {
          flight.image = this.picture(flight)
        }
        this.filterUniqueDestinations();
      })
      .catch((error) => console.error("Error loading flights:", error));

  },
  methods: {
    picture(flight) {
      let flight_name = flight.destination
      switch (flight_name) {
        case "Dubai":
          return "src/assets/Dubai.jpeg"
        case "London":
          return "src/assets/London.jpg"
        case "Los Angeles":
          return "src/assets/Los_Angeles.jpg"
        case "New York":
          return "src/assets/New_York.jpg"
        case "Paris":
          return "src/assets/Paris.jpg"
        case "Sydney":
          return "src/assets/Sydney.jpeg"
        case "Tokyo":
          return "src/assets/Tokyo.jpg"
        default:
          return "src/assets/hotel.jpg"
      }
    },
    navigateToFlight(id) {
      this.$router.push(`/flight/${id}`);
    },
    fetchFlightsByFilter() {

      let baseUrl = "http://localhost:3000/api/flights/";
      let params = [];

      if (this.search.destination) params.push(`destination/${this.search.destination}`);
      if (this.search.date) params.push(`date/${this.search.date}`);
      if (this.search.time) params.push(`time/${this.search.time}`);
      params.push(`price_min/${this.search.price_min}`);
      params.push(`price_max/${this.search.price_max}`);

      // Construct the final URL
      let url = baseUrl + (params.length ? params.join("/") : "");

      // Make the fetch request
      fetch(url)
        .then((response) => response.json())
        .then((data) => {
          this.flights = data
          for (let flight of this.flights) {
            flight.image = this.picture(flight)
          }
        })
        .catch((err) => console.error("Error fetching flights:", err.message));

    },
    filterUniqueDestinations() {
      this.uniqueDestinations = [...new Set(this.flights.map(flight => flight.destination))];
    },
  },
};
</script>

<style scoped>

ul {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-template-rows: repeat(5, 1fr);
  grid-column-gap: 10px;
  grid-row-gap: 0px;
}

.card {
  display: flex;
  flex-direction: column;
  gap: 10px;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  background-color: #f9f9f9;
  max-width: 400px;
  margin: auto;
}

.flight {
  display: flex;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  background-color: #f9f9f9;
  max-width: 300px;
  transition: 0.3s ease-in-out;
  height: 80px;
}

.flight:hover {
  transform: scale(1.1);
  cursor: pointer;
  transition: 0.3s ease-in-out;
}

input,
select {
  width: 100%;
  padding: 8px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button {
  margin-top: 10px;
  padding: 10px;
  font-size: 16px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}

/* Remove number input arrows */
input[type="number"]::-webkit-inner-spin-button,
input[type="number"]::-webkit-outer-spin-button {
  -webkit-appearance: none;
  margin: 0;
}
input[type="number"] {
  -moz-appearance: textfield;
}

.item {
  list-style: none;
  margin-top: 10px;
}

ul {
  margin: auto;
  padding: 0;
}

.side-by-side {
  align-self: center;
  display: flex;
}

.price {
  width: 50px;
  height: 10px;
}

p {
  margin: 0;
}

.hyphen {
  margin-left: 7px;
  margin-right: 7px;
}

.sorting {
  margin-top: 10px;
  justify-content: center;
  display: flex;
}

#sort {
  width: 200px;
}

.sorting-label {
  margin-right: 10px;
}

.amount_of_flights {
  display: flex;
  justify-content: center;
  margin-top: 10px;
}

img {
  margin-right: 5px;
  width: 50%;
}

.flight_details {
  display: flex;
  flex-direction: column;
}

.date_and_time {
  display: flex;
}

@media (max-width: 950px) {
  ul {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    grid-template-rows: repeat(5, 1fr);
    grid-column-gap: 10px;
    grid-row-gap: 0px;
  }
}

@media (max-width: 725px) {
  ul {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    grid-template-rows: repeat(5, 1fr);
    grid-column-gap: 10px;
    grid-row-gap: 0px;
  }
}

select:hover, #date, #time {
  cursor: pointer;
}

option 

@media (max-width: 500px) {
  ul {
    display: grid;
    grid-template-columns: repeat(1, 1fr);
    grid-template-rows: repeat(5, 1fr);
    grid-column-gap: 10px;
    grid-row-gap: 0px;
    align-items: center;
  }
}
</style>
