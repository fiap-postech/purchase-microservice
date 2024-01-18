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

executeCommand('kubectl delete --all pods');
executeCommand('kubectl delete --all services');
executeCommand('kubectl delete --all deployments');
executeCommand('kubectl delete --all cm');
executeCommand('kubectl delete --all rs');

executeCommand('kubectl delete --all pv');
executeCommand('kubectl delete --all pvc ');
executeCommand('kubectl delete --all secret');
executeCommand('kubectl delete --all sc ');
executeCommand('kubectl delete --all cm');
executeCommand('kubectl delete --all hpa');
executeCommand('kubectl delete --all jobs ');
executeCommand('kubectl delete --all cronjobs');
//  executeCommand('docker stop $(docker ps -a -q)');
//  executeCommand('docker rm $(docker ps -a -q)');