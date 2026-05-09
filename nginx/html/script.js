const API_URL = '/api/tasks';

async function loadTasks() {
    try {
        const response = await fetch(API_URL);
        const tasks = await response.json();
        const list = document.getElementById('taskList');
        list.innerHTML = '';
        tasks.forEach(task => {
            const item = document.createElement('li');
            item.innerHTML = `<span>${task.title}</span>`;
            list.appendChild(item);
        });
        console.log('Задачи успешно загружены');
    } catch (error) {
        console.error('Ошибка загрузки задач:', error);
    }
}

async function addTask() {
    const input = document.getElementById('taskInput');
    const title = input.value.trim();
    if (!title) return;

    try {
        await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ title: title, completed: false })
        });
        input.value = '';
        await loadTasks();
        console.log(`Задача "${title}" добавлена`);
    } catch (error) {
        console.error('Ошибка добавления задачи:', error);
    }
}

window.onload = loadTasks;