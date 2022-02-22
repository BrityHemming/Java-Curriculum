
# Java Fundamentals

This repository is a template for creating a repository for your classwork.

## Creating a Repository for Your Classwork

**Before starting creating your repository, be sure that you've supplied your GitHub account name to your instructor or TA and that you've been added to the `dev10-program` organization.**

1. Visit https://github.com/new to create a new repository
2. Under the "Repository template" field, Select the `dev10-program/java-fundamentals-student-template` repository template
3. Select your GitHub account name in the "Owner" select list
4. Give your repository a name (e.g. `dev10-classwork` or something similar)
5. Choose to make your repository "Private"
6. Click "Create repository" to create your repository

_For more information about creating a repository, see https://docs.github.com/en/free-pro-team@latest/github/getting-started-with-github/create-a-repo_

### Adding Your Instructor and TA as Collaborators

After creating your repository, complete the following steps to add your instructor and TA as collaborators:

1. From the home page for your GitHub repository, browse to the "Settings" tab
2. On the left, click on "Manage access"
3. Click on the "Invite a collaborator" button
4. In the popup window, type your instructor's GitHub account name
5. Click the "Add {account name} to this repository" button to complete the process
7. Repeat steps #3-5 to add your TA as a collaborator

## Cloning Your Repo

Before you begin working on your classwork, you need to clone your GitHub repository to your local machine.

1. Open a terminal window and use the `cd` command to browse to the folder where you want to clone your repository to
2. Use the `git clone` command to clone your repository to your local machine

```
git clone https://github.com/{account name}/{repo name}.git 
```

_Note: replace `{account name}` with your GitHub account name and `{repo name}` with the name of your GitHub repository. You can copy the URL to your GitHub repo to the clipboard by clicking on the "Code" button from your repo's home page and then clicking the clipboard button to the right of your repo's URL._

After Git has completed cloning the repository your local machine, you can use IntelliJ to open any of the projects contained within the repository.

**Important: Don't attempt to open the entire repository into IntelliJ! Only open a single project's folder at a time.**

## Creating and Pushing a Commit

To backup ongoing work or to share work with your instructor or TA, complete the following steps to create and push a commit:

1. Use the `cd` command to browse to your repository's folder (e.g. `cd dev10-classwork`)
2. From the terminal, use the `git add .` command to stage all of your uncommitted changes
3. Use the `git commit -m "Latest code changes"` command to create a commit
  * Avoid the temptation to always use a generic commit message like "Latest code changes"
  * Using a specific commit message, such as "Update variables and types exercises", will make it easier to locate a specific commit when reviewing the history for your repository
4. Use the `git push` command to push your new commit to GitHub
5. Browse to your repository on GitHub and confirm that your changes are available within the remote repository
