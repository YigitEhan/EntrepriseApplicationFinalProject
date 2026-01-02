# 📦 Git Setup & GitHub Push Guide

This guide will help you initialize Git, commit your Phase A code, and push it to GitHub.

---

## 🔧 Prerequisites

1. **Git installed** - Check with: `git --version`
2. **GitHub account** - Create one at https://github.com if you don't have one
3. **Git configured** with your name and email

---

## ⚙️ Step 1: Configure Git (First Time Only)

```bash
# Set your name (use your real name for university projects)
git config --global user.name "Yigit"

# Set your email (use your university email)
git config --global user.email "yigit@university.edu"

# Verify configuration
git config --list
```

---

## 📁 Step 2: Initialize Git Repository

```bash
# Navigate to project directory
cd C:\EntrepriseApplicationFinalProject

# Initialize Git repository
git init

# Verify .git folder was created
ls -Force  # PowerShell
# or
dir /a     # CMD
```

---

## 📝 Step 3: Stage All Files

```bash
# Add all files to staging area
git add .

# Check what will be committed
git status
```

You should see files like:
- `pom.xml`
- `src/main/java/...`
- `src/main/resources/...`
- `README.md`
- etc.

---

## 💾 Step 4: Create Initial Commit

```bash
# Commit with a descriptive message
git commit -m "feat: Complete Phase A - Core application setup

- Add domain entities (User, Role, Product, Category)
- Implement JPA repositories with custom queries
- Create service layer for business logic
- Build catalog controller with category filtering
- Configure H2 database and Spring Security
- Seed database with 4 categories and 12 products
- Design responsive catalog UI with Thymeleaf
- Add comprehensive documentation and oral defense guide

Phase A deliverables: Complete
Author: Yigit (University Student)
Course: Enterprise Applications"
```

---

## 🌐 Step 5: Create GitHub Repository

### Option A: Via GitHub Website

1. Go to https://github.com
2. Click the **"+"** icon → **"New repository"**
3. Repository name: `arts-equipment-rental` or `enterprise-app-project`
4. Description: `Equipment rental platform for arts education - University Project`
5. **Keep it Public** (for university submission) or Private (if preferred)
6. **DO NOT** initialize with README, .gitignore, or license (we already have these)
7. Click **"Create repository"**

### Option B: Via GitHub CLI (if installed)

```bash
gh repo create arts-equipment-rental --public --source=. --remote=origin
```

---

## 🔗 Step 6: Connect Local Repository to GitHub

After creating the GitHub repository, you'll see instructions. Use these commands:

```bash
# Add GitHub repository as remote
git remote add origin https://github.com/YOUR_USERNAME/arts-equipment-rental.git

# Verify remote was added
git remote -v
```

Replace `YOUR_USERNAME` with your actual GitHub username.

---

## 🚀 Step 7: Push to GitHub

```bash
# Push to GitHub (first time)
git push -u origin main

# Or if your default branch is 'master'
git push -u origin master
```

If you get an error about branch names, rename your branch:

```bash
# Rename branch to 'main'
git branch -M main

# Then push
git push -u origin main
```

---

## 🔐 Authentication

GitHub may ask for authentication:

### Option 1: Personal Access Token (Recommended)

1. Go to GitHub → Settings → Developer settings → Personal access tokens → Tokens (classic)
2. Click "Generate new token (classic)"
3. Give it a name: "University Project"
4. Select scopes: `repo` (full control of private repositories)
5. Click "Generate token"
6. **Copy the token** (you won't see it again!)
7. When Git asks for password, paste the token

### Option 2: GitHub CLI

```bash
# Install GitHub CLI, then authenticate
gh auth login
```

### Option 3: SSH Key

```bash
# Generate SSH key
ssh-keygen -t ed25519 -C "yigit@university.edu"

# Add to GitHub: Settings → SSH and GPG keys → New SSH key
# Copy the public key:
cat ~/.ssh/id_ed25519.pub

# Change remote to SSH
git remote set-url origin git@github.com:YOUR_USERNAME/arts-equipment-rental.git
```

---

## ✅ Step 8: Verify Push

1. Go to your GitHub repository URL
2. You should see all your files
3. Check that README.md is displayed on the main page
4. Verify the commit message is visible

---

## 📊 Step 9: Add Repository Description & Topics

On GitHub repository page:

1. Click **"About"** (gear icon)
2. **Description:** `Equipment rental platform for arts education institute - Spring Boot, JPA, Thymeleaf`
3. **Topics:** `spring-boot`, `java`, `thymeleaf`, `jpa`, `h2-database`, `university-project`, `enterprise-applications`
4. **Save changes**

---

## 🔄 Future Commits (Phase B and beyond)

```bash
# After making changes
git add .

# Commit with descriptive message
git commit -m "feat: Add user authentication and login functionality"

# Push to GitHub
git push
```

---

## 📋 Useful Git Commands

```bash
# Check status
git status

# View commit history
git log --oneline

# View changes before committing
git diff

# Undo changes to a file (before staging)
git checkout -- filename

# Undo last commit (keep changes)
git reset --soft HEAD~1

# View remote repositories
git remote -v

# Pull latest changes from GitHub
git pull origin main
```

---

## 🎓 For University Submission

### Include in Your Submission

1. **GitHub Repository URL**
   - Example: `https://github.com/yigit/arts-equipment-rental`

2. **Clone Instructions**
   ```bash
   git clone https://github.com/YOUR_USERNAME/arts-equipment-rental.git
   cd arts-equipment-rental
   mvnw.cmd spring-boot:run
   ```

3. **Commit History**
   - Shows your development process
   - Demonstrates version control knowledge

### README Badge (Optional but Professional)

Add to top of README.md:

```markdown
![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.1-brightgreen)
![License](https://img.shields.io/badge/License-University%20Project-blue)
```

---

## 🐛 Troubleshooting

### Issue: "fatal: not a git repository"
**Solution:** Run `git init` in the project directory

### Issue: "remote origin already exists"
**Solution:** 
```bash
git remote remove origin
git remote add origin YOUR_GITHUB_URL
```

### Issue: "failed to push some refs"
**Solution:**
```bash
git pull origin main --rebase
git push origin main
```

### Issue: "Permission denied (publickey)"
**Solution:** Use HTTPS instead of SSH, or set up SSH keys properly

---

## 📝 Sample .gitignore (Already Included)

Your `.gitignore` file already excludes:
- `target/` - Build output
- `.idea/` - IntelliJ files
- `.vscode/` - VS Code files
- `*.class` - Compiled files
- `*.log` - Log files

---

## ✅ Checklist Before Pushing

- [ ] All files are committed
- [ ] `.gitignore` is properly configured
- [ ] No sensitive data (passwords, API keys) in code
- [ ] README.md is complete and professional
- [ ] Code compiles without errors
- [ ] Application runs successfully
- [ ] Commit message is descriptive

---

## 🎯 Final Git Commands Summary

```bash
# One-time setup
git init
git add .
git commit -m "feat: Complete Phase A - Core application setup"
git remote add origin https://github.com/YOUR_USERNAME/arts-equipment-rental.git
git push -u origin main

# Future updates
git add .
git commit -m "Your commit message"
git push
```

---

**Good luck with your project! 🚀**

**Remember:** Git is not just for submission - it's a professional skill that will help you throughout your career!

