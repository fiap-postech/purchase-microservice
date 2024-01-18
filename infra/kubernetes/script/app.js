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

executeCommand('kubectl apply -f infra/kubernetes/app/app-deployment.yaml');
executeCommand('kubectl apply -f infra/kubernetes/app/app-service.yaml');
executeCommand('kubectl apply -f infra/kubernetes/app/app-hpa.yaml');