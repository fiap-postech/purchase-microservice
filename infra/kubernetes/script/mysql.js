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

executeCommand('kubectl apply -f infra/kubernetes/mysql/mysql-secret.yaml');
executeCommand('kubectl apply -f infra/kubernetes/mysql/mysql-initdb-persistent-volume.yaml');
executeCommand('kubectl apply -f infra/kubernetes/mysql/mysql-initdb-persistent-volume-claim.yaml');
executeCommand('kubectl apply -f infra/kubernetes/mysql/mysql-persistent-volume.yaml');
executeCommand('kubectl apply -f infra/kubernetes/mysql/mysql-persistent-volume-claim.yaml');
executeCommand('kubectl apply -f infra/kubernetes/mysql/mysql-deployment.yaml');
executeCommand('kubectl apply -f infra/kubernetes/mysql/mysql-service.yaml');