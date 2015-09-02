"""

Hi, Udacity reviewer! Welcome to my project 2 submission. This project creates
a HTML page with helpful information on the nature of programming! The content
is stored in content.json, and is parsed into Python data structures in
parsing.py. My HTML templates are in /templates, and templating.py inserts the
content into the templates.

This directory is also supplied with styles.css, which gives the page a fancy,
responsive design based on flexboxes.

I hope you enjoy my project!

"""

import output
import templating
import parsing

def main():
    raw_content = parsing.parse_content()["concepts"]
    templated_concepts = [
        templating.apply_template("templates/concept.html", **concept)
        for concept in raw_content]
    concatinated_concepts = "".join(templated_concepts)
    content = templating.apply_template("templates/page.html",
        content = concatinated_concepts)
    output.write_output(content)

if __name__ == '__main__':
    main()
