const { db } = require("../config/firebase");

const getDeadlines = async (req, res) => {
    try {
        const { userId } = req.params;

        const snapshot = await db
            .collection("users")
            .doc(userId)
            .collection("deadlines")
            .get();

        const deadlines = snapshot.docs.map(doc => ({
            id: doc.id,
            ...doc.data()
        }));

        res.status(200).json({
            success: true,
            count: deadlines.length,
            data: deadlines
        });
    } catch (error) {
        console.error(error);

        res.status(500).json({
            success: false,
            message: "Failed to retrieve deadlines",
            error: error.message
        });
    }
};

const createDeadline = async (req, res) => {
    try {
        const {
            userId,
            title,
            category,
            description,
            priority,
            dueDateTime
        } = req.body;

        if (!userId) {
            return res.status(400).json({
                success: false,
                message: "userId is required"
            });
        }

        if (!title) {
            return res.status(400).json({
                success: false,
                message: "title is required"
            });
        }

        if (!category) {
            return res.status(400).json({
                success: false,
                message: "category is required"
            });
        }

        if (!priority) {
            return res.status(400).json({
                success: false,
                message: "priority is required"
            });
        }

        if (!dueDateTime) {
            return res.status(400).json({
                success: false,
                message: "dueDateTime is required"
            });
        }

        const deadline = {
            title,
            category,
            description: description || "",
            priority,
            dueDateTime: Number(dueDateTime),
            completed: false,
            createdAt: Date.now()
        };

        const documentReference = await db
            .collection("users")
            .doc(userId)
            .collection("deadlines")
            .add(deadline);

        res.status(201).json({
            success: true,
            message: "Deadline created successfully",
            id: documentReference.id,
            data: deadline
        });

    } catch (error) {
        console.error(error);

        res.status(500).json({
            success: false,
            message: "Failed to create deadline",
            error: error.message
        });
    }
};

const updateDeadline = async (req, res) => {
    try {
        const {
            userId,
            deadlineId
        } = req.params;

        const updates = req.body;

        if (Object.keys(updates).length === 0) {
            return res.status(400).json({
                success: false,
                message: "No update data supplied"
            });
        }

        await db
            .collection("users")
            .doc(userId)
            .collection("deadlines")
            .doc(deadlineId)
            .update(updates);

        res.status(200).json({
            success: true,
            message: "Deadline updated successfully"
        });

    } catch (error) {
        console.error(error);

        res.status(500).json({
            success: false,
            message: "Failed to update deadline",
            error: error.message
        });
    }
};

const deleteDeadline = async (req, res) => {
    try {
        const {
            userId,
            deadlineId
        } = req.params;

        await db
            .collection("users")
            .doc(userId)
            .collection("deadlines")
            .doc(deadlineId)
            .delete();

        res.status(200).json({
            success: true,
            message: "Deadline deleted successfully"
        });

    } catch (error) {
        console.error(error);

        res.status(500).json({
            success: false,
            message: "Failed to delete deadline",
            error: error.message
        });
    }
};

module.exports = {
    getDeadlines,
    createDeadline,
    updateDeadline,
    deleteDeadline
};