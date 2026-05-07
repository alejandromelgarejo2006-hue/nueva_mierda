@RestController
@RequestMapping("/hogwarts/casas")
public class CasaController {

    private final CasaService casaService;

    public CasaController(CasaService casaService) {
        this.casaService = casaService;
    }

    @GetMapping
    public ResponseEntity<List<Casa>> getAll() {
        List<Casa> casas = casaService.obtenerListaCasas();

        return (casas == null || casas.isEmpty())
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(casas);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Casa> getById(@PathVariable Integer id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }

        Casa casa = casaService.obtenerCasaPorClave(id);

        return (casa != null)
                ? ResponseEntity.ok(casa)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Casa>> getByNombre(@PathVariable String nombre) {
        if (nombre == null || nombre.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        List<Casa> casas = casaService.obtenerCasaPorNombre(nombre);

        return (casas == null || casas.isEmpty())
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(casas);
    }
}
