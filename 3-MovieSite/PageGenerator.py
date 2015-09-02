from jinja2 import Environment, FileSystemLoader
import json

class PageGenerator:
    """ Provides glue between JSON data and Jinja templates """

    env = None

    def __init__(self, templates_path):
        self.env = Environment(loader=FileSystemLoader(templates_path))

    def parse_data(self, data_path):
        """ Parses a JSON file at a given path. """
        with open(data_path) as data_file:
            return json.loads(data_file.read())

    def write_output(self, output_text, output_path):
        """ Writes a string to a file at a given path. """
        with open(output_path,"w+") as output_file:
            output_file.write(output_text)

    def generate_page(self, data_path, template_name, output_path):
        """ Renders a template, passing data from a specified file """
        data = self.parse_data(data_path)
        content = self.env.get_template(template_name).render(data = data)
        self.write_output(content, output_path)


def main():
    generator = PageGenerator("templates")
    generator.generate_page("videos.json",
         "page.html",
         "build/JeremyProject3.html"
         )

if __name__ == '__main__':
    main()
