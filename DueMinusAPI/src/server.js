require("dotenv").config();

const express = require("express");
const cors = require("cors");

const deadlineRoutes =
    require("./routes/deadlineRoutes");

const app = express();

const PORT = process.env.PORT || 3000;

app.use(cors());

app.use(express.json());

app.get("/", (req, res) => {
    res.status(200).json({
        success: true,
        message: "DueMinus REST API is running"
    });
});

app.get("/api/health", (req, res) => {
    res.status(200).json({
        success: true,
        message: "DueMinus API is healthy"
    });
});

app.use(
    "/api/deadlines",
    deadlineRoutes
);

app.use((req, res) => {
    res.status(404).json({
        success: false,
        message: "API endpoint not found"
    });
});

app.listen(PORT, () => {
    console.log(
        `DueMinus REST API running on http://localhost:${PORT}`
    );
});