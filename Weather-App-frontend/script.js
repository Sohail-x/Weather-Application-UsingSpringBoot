const API_URL = "https://creative-creativity-production-9e38.up.railway.app/weather/forecast";

// DOM elements
const cityInput = document.getElementById("cityInput");
const searchBtn = document.getElementById("searchBtn");
const daysSelect = document.getElementById("daysSelect");

const loading = document.getElementById("loading");
const errorMessage = document.getElementById("errorMessage");
const errorText = document.getElementById("errorText");

const cityName = document.getElementById("cityName");
const locationDetails = document.getElementById("locationDetails");
const localTime = document.getElementById("localTime");

const currentTemp = document.getElementById("currentTemp");
const weatherText = document.getElementById("weatherText");
const weatherIcon = document.getElementById("weatherIcon");

const humidity = document.getElementById("humidity");
const wind = document.getElementById("wind");
const condition = document.getElementById("condition");

const forecastContainer = document.getElementById("forecastContainer");
const forecastTitle = document.getElementById("forecastTitle");
const forecastBadge = document.getElementById("forecastBadge");

function getWeatherIcon(weather = "") {
    const text = weather.toLowerCase();

    if (text.includes("sunny")) return "☀️";
    if (text.includes("clear")) return "🌙";
    if (text.includes("partly cloudy")) return "⛅";
    if (text.includes("cloudy") || text.includes("overcast")) return "☁️";
    if (text.includes("rain") || text.includes("drizzle")) return "🌧️";
    if (text.includes("thunder")) return "⛈️";
    if (text.includes("snow")) return "❄️";
    if (text.includes("mist") || text.includes("fog")) return "🌫️";

    return "🌤️";
}

function formatDate(dateString) {
    const date = new Date(`${dateString}T00:00:00`);

    return date.toLocaleDateString("en-US", {
        day: "numeric",
        month: "short",
        year: "numeric"
    });
}

function getDayName(dateString, index) {
    if (index === 0) return "Today";

    const date = new Date(`${dateString}T00:00:00`);

    return date.toLocaleDateString("en-US", {
        weekday: "long"
    });
}

function showError(message) {
    errorText.textContent = message;
    errorMessage.classList.remove("hidden");
}

async function getWeather(city, days) {
    try {
        loading.classList.remove("hidden");
        errorMessage.classList.add("hidden");

        const url =
            `${API_URL}?city=${encodeURIComponent(city)}&days=${days}`;

        console.log("Request URL:", url);

        const response = await fetch(url);

        if (!response.ok) {
            throw new Error(`API returned ${response.status}`);
        }

        const data = await response.json();

        console.log("API Response:", data);

        if (!data.weatherResponce || !Array.isArray(data.dayTemp)) {
            throw new Error("Unexpected API response format.");
        }

        updateCurrentWeather(data.weatherResponce);
        updateForecast(data.dayTemp);

    } catch (error) {
        console.error("Weather error:", error);

        showError(
            "Unable to fetch weather. Make sure your Spring Boot server is running and CORS is enabled."
        );
    } finally {
        loading.classList.add("hidden");
    }
}

function updateCurrentWeather(weather) {
    cityName.textContent = weather.city || "--";

    locationDetails.textContent =
        `${weather.region || "--"}, ${weather.country || "--"}`;

    localTime.textContent =
        weather.localtime || "--";

    currentTemp.textContent =
        weather.temp_c !== undefined
            ? Number(weather.temp_c).toFixed(1)
            : "--";

    weatherText.textContent =
        weather.text || "--";

    condition.textContent =
        weather.text || "--";

    humidity.textContent =
        weather.humidity ?? "--";

    wind.textContent =
        weather.wind_kph ?? "--";

    weatherIcon.textContent =
        getWeatherIcon(weather.text);
}

function updateForecast(forecast) {
    forecastContainer.innerHTML = "";

    const numberOfDays = forecast.length;

    forecastTitle.textContent =
        `${numberOfDays}-Day Forecast`;

    forecastBadge.textContent =
        `📅 ${numberOfDays} Days`;

    if (numberOfDays === 0) {
        forecastContainer.innerHTML =
            `<p class="empty-message">No forecast data available.</p>`;
        return;
    }

    forecast.forEach((day, index) => {
        const card = document.createElement("div");

        card.className =
            `forecast-card ${index === 0 ? "today" : ""}`;

        // Your current API only provides temperature fields,
        // not a forecast condition for every day.
        // Therefore a neutral weather icon is used here.
        const icon = index === 0
            ? getWeatherIcon("Sunny")
            : "🌤️";

        card.innerHTML = `
            <div class="forecast-day">
                ${getDayName(day.date, index)}
            </div>

            <div class="forecast-date">
                ${formatDate(day.date)}
            </div>

            <div class="forecast-icon">
                ${icon}
            </div>

            <div class="forecast-temp">
                <span class="max-temp">
                    ${Number(day.max_temp).toFixed(1)}°
                </span>

                <span class="min-temp">
                    ${Number(day.mintemp_c).toFixed(1)}°
                </span>
            </div>

            <div class="avg-temp">
                Average:
                ${Number(day.avgtemp_c).toFixed(1)}°C
            </div>
        `;

        forecastContainer.appendChild(card);
    });
}

function searchWeather() {
    const city = cityInput.value.trim();
    const days = Number(daysSelect.value);

    if (!city) {
        showError("Please enter a city name.");
        cityInput.focus();
        return;
    }

    if (!days || days < 1) {
        showError("Please select a valid number of forecast days.");
        return;
    }

    getWeather(city, days);
}

searchBtn.addEventListener("click", searchWeather);

cityInput.addEventListener("keydown", (event) => {
    if (event.key === "Enter") {
        searchWeather();
    }
});

// Automatically reload forecast when the selected number of days changes.
daysSelect.addEventListener("change", () => {
    searchWeather();
});

// Initial request
getWeather(cityInput.value, Number(daysSelect.value));
