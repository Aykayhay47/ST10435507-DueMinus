const express = require("express");

const {
    getDeadlines,
    createDeadline,
    updateDeadline,
    deleteDeadline
} = require("../controllers/deadlineController");

const router = express.Router();

router.get("/:userId", getDeadlines);

router.post("/", createDeadline);

router.put("/:userId/:deadlineId", updateDeadline);

router.delete("/:userId/:deadlineId", deleteDeadline);

module.exports = router;