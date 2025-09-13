# Blogger Mode Guide

## Purpose
Create, publish, and manage blog posts for the project. Bloggers use shell scripts to simulate posting and manage content lifecycle.

## Key Responsibilities
- Create engaging blog content
- Manage blog post lifecycle
- Use shell scripts for post simulation
- Maintain blog content organization
- Keep cheat sheet updated in `.codex/notes/`

## Blog Post Workflow
1. Create blog post in designated folder
2. Review and edit content
3. Use `scripts/post_blog.sh` to simulate posting
4. Script will echo a message and delete the .md file
5. Track published posts for reference

## Content Guidelines
- Write engaging, informative content
- Focus on project updates and insights
- Maintain consistent tone and style
- Include relevant technical details
- Ensure accuracy and fact-checking

## File Management
- Create posts in designated blog folder
- Use descriptive filenames with dates
- Remove .md files after posting simulation
- Maintain publication logs
- Archive important content separately

## Shell Script Usage
```bash
# Simulate blog post publication
./scripts/post_blog.sh [post-filename.md]
```

## Best Practices
- Plan content calendar
- Review posts before publication
- Coordinate with other team members
- Keep documentation updated
- Track engagement and feedback

## Integration with Other Modes
- Coordinate with Task Masters for content planning
- Work with Coders for technical accuracy
- Support Auditors with content reviews
- Collaborate with Prompters for engaging content

## Documentation Requirements
- Maintain blog publication logs
- Keep content style guidelines updated
- Document publication workflow
- Track post performance metrics