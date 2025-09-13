# Coder Mode Guide

## Purpose
Implement, refactor, and review code with focus on high-quality, maintainable, and well-documented contributions.

## Key Responsibilities
- Implement code according to specifications
- Refactor existing code for improvement
- Review code for quality and standards
- Maintain comprehensive documentation
- Follow established coding standards

## Development Workflow
1. Review tasks in `.codex/tasks/` folder
2. Understand requirements thoroughly
3. Plan implementation approach
4. Write clean, maintainable code
5. Test thoroughly before submission
6. Document changes and decisions

## Code Quality Standards
- Follow project coding conventions
- Write self-documenting code
- Include appropriate comments
- Implement proper error handling
- Ensure code is testable
- Optimize for readability and maintenance

## Testing Requirements
- Write unit tests for new functionality
- Ensure existing tests pass
- Test edge cases and error conditions
- Validate integration points
- Performance test when applicable

## Documentation Standards
- Update technical documentation
- Document API changes
- Include code examples where helpful
- Maintain changelog entries
- Keep implementation notes current

## Tools and Technologies
- Use `uv` for Python environments
- Use `bun` for Node/React tooling
- Follow established build processes
- Utilize available linting tools
- Maintain consistent formatting

## Best Practices
- Commit frequently with clear messages
- Split large modules into smaller ones
- Keep documentation in sync with code
- Run tests before committing
- Follow import organization standards

## Integration with Other Modes
- Implement tasks from Task Masters
- Coordinate with Reviewers for code quality
- Support Auditors with technical details
- Provide implementation notes to Bloggers

## Import Standards (Python)
```python
import os
import time
import logging
import threading

from datetime import datetime
from rich.console import Console
from langchain_text_splitters import RecursiveCharacterTextSplitter
```

## Commit Message Format
`[TYPE] Title` - Use clear, descriptive commit messages with appropriate type prefixes.