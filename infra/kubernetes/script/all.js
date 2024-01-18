const { exec } = require('child_process');

// Function to execute the kubectl command
function executeCommand(command) {
    exec(command, (error, stdout, stderr) => {
        if (error) {
            console.error(`Error executing command: ${error.message}`);
            return;
        }
        if (stderr) {
            console.error(`Command stderr: ${stderr}`);
            return;
        }
        console.log(`${stdout}`);
    });
}
executeCommand('kubectl apply -f infra/kubernetes/components.yaml');
executeCommand('node infra/kubernetes/script/cdn.js');
executeCommand('node infra/kubernetes/script/redis.js');
executeCommand('node infra/kubernetes/script/mysql.js');
executeCommand('sleep 60 && node infra/kubernetes/script/app.js');